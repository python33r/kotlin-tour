#!/bin/bash
sed -e 's/\/\/show-notes/show-notes/' -e 's/\/\/handout/handout/' slides.typ > tmp.typ
typst compile tmp.typ
cpdf -cropbox "841.89pt 0pt 841.89pt 473.563pt" tmp.pdf -o notes.pdf
rm -f tmp.pdf, tmp.typ
