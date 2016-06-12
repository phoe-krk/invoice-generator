(defparameter *invoice-header* "
\\documentclass[12pt]{article}
\\usepackage[MeX]{polski}
\\usepackage[latin2]{inputenc}
\\usepackage{geometry}
\\geometry{top=20mm, bottom=20mm}

\\setlength{\\marginparwidth}{0pt}
\\setlength{\\parindent}{0pt}
\\addtolength{\\hoffset}{-0.6in}
\\pagestyle{empty}")

(defparameter *invoice-data* "
\\newcommand{\\datasprzedazy}{~A}
\\newcommand{\\datawystawienia}{~A}
\\newcommand{\\terminplatnosci}{~A}
\\newcommand{\\formaplatnosci}{~A}
\\newcommand{\\nrfaktury}{~A} 
\\newcommand{\\netto}{~,2F} 
\\newcommand{\\vat}{~,2F}
\\newcommand{\\brutto}{~,2F}")

(defparameter *invoice-who* "
\\begin{document}

\\begin{tabular}{p{0.8\\textwidth} l l}
\\textit{Sprzedawca:} & Data sprzedaży: & \\datasprzedazy \\\\
& Data wystawienia: & \\datawystawienia \\\\
~A & & \\\\
~A & & \\\\
~A & & \\\\
~A & &
\\end{tabular}
\\ \\\\ \\ \\\\
\\centerline{\\hspace{50pt}\\LARGE{Faktura VAT nr \\nrfaktury}}\\\\
\\centerline{\\hspace{50pt}(oryginał / kopia)}\\\\
\\textit{Nabywca:} \\\\
\\ \\\\
~A \\\\
~A \\\\
~A \\\\
~A \\\\

\\begin{tabular}{r l l r p{2.5cm} p{2.5cm} l r p{2.5cm}}
\\hline 
Lp & Nazwa usługi & j.m. & Ilość & Cena jedn.\\newline netto 
   & Wartość\\newline netto & VAT & Podatek & Wartość\\newline z podatkiem \\\\ \\hline")

(defparameter *invoice-footer* "
\\\\
  
\\end{tabular}

\\ \\\\
Forma płatności: \\formaplatnosci \\\\
Termin płatności: \\terminplatnosci \\\\

\\ \\\\ \\ \\\\ \\ \\\\
..............................\\hspace{325pt}..............................\\\\
\\ \\ \\ Osoba odbierająca\\hspace{325pt}\\mbox{Osoba wystawiająca}

\\end{document}")

(defparameter *example-data*
  '(("3.03.2006" "3.03.2006" "17.03.2006" "PRZELEW" "3/2006")
    ("Seba Tibia" "3/03 Gała" "12-345 Peja" "NIP: 1231231231")
    ("Rychu" "Karagrad" "Rosja Stalinowska" "")
    (("Sebastian" "szt" 5 250/100 23)
     ("Piwo" "szt" 12 150/100 8))))

(defun sum-items (items)
  (let ((result-netto 0)
	(result-vat 0)
	(result-brutto 0))
    (dolist (item items)
      (destructuring-bind (nazwa jm ilosc netto vat) item
	(declare (ignorable nazwa jm ilosc netto vat))
	(incf result-netto (* ilosc netto))
	(incf result-vat (* (/ vat 100) ilosc netto))
	(incf result-brutto (* (1+ (/ vat 100)) ilosc netto))))
    (values result-netto result-vat result-brutto)))

(defun cat (&rest sequences)
  (apply #'concatenate 'string sequences))

(defun print-header (data)  
  (destructuring-bind
      ((data-s data-w termin forma numer)
       (s1 s2 s3 s4)
       (n1 n2 n3 n4)
       towary) data
    (multiple-value-bind (netto vat brutto) (sum-items towary)
      (cat (format nil *invoice-header*)
	   (format nil *invoice-data*
		   data-s data-w termin forma numer
		   netto vat brutto)
	   (format nil *invoice-who* s1 s2 s3 s4 n1 n2 n3 n4)))))

(defun print-items (data) 
  (let (result-strings
	(i 0)
	(w-tym "W tym:")
	(items (fourth data))
	(netto-sums (make-hash-table))
	(vat-sums (make-hash-table))
	(brutto-sums (make-hash-table))) 
    (dolist (item items)
      (incf i)
      (destructuring-bind (nazwa jm ilosc netto vat) item
	(setf (gethash vat netto-sums 0) (+ (gethash vat netto-sums 0)
					    (* ilosc netto))
	      (gethash vat vat-sums 0) (+ (gethash vat vat-sums 0)
					  (* ilosc (/ vat 100) netto))
	      (gethash vat brutto-sums 0) (+ (gethash vat brutto-sums 0)
					     (* ilosc (1+ (/ vat 100)) netto)))
	(push (format nil "~%~A & ~A & ~A & ~A & ~,2F & ~,2F & ~A \\% & ~,2F & ~,2F \\\\ \\hline"
		      i nazwa jm ilosc netto (* ilosc netto) vat
		      (* ilosc (/ vat 100) netto) (* ilosc (1+ (/ vat 100)) netto))
	      result-strings)))
    (multiple-value-bind (netto vat brutto) (sum-items items)
      (push (format nil "~%& & & & Razem: & ~,2F & & ~,2F & ~,2F \\\\" netto vat brutto)
	    result-strings))
    (dolist (stawka '(0 5 8 23)) 
      (unless (= 0 (gethash stawka netto-sums 0))
	(push (format nil "~%& & & & ~A & ~,2F & ~A \\% & ~,2F & ~,2F \\\\"
		      (prog1 w-tym
			(setf w-tym "      "))
		      (gethash stawka netto-sums 0) stawka
		      (gethash stawka vat-sums 0)
		      (gethash stawka brutto-sums 0))
	      result-strings)))
    (apply #'cat (nreverse result-strings))))

(defun print-footer ()
  (format nil *invoice-footer*))

(defun print-all (data)
  (cat (print-header data)
       (print-items data)
       (print-footer)))
