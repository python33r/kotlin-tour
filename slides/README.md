# slides

[Typst][typ] slides for 'Tour of Kotlin' presentation.

To generate the PDF, make sure the Typst binary is in your PATH, then do

    typst c slides.typ

For a fully accurate render without warnings, make sure that the
[Fira Code][fc] font is installed on your system.

To generate a cropped PDF containing only speaker notes, do

    ./notes.sh

(Note: this requires that [cpdf][cp] be installed and in your PATH.)

[typ]: https://github.com/typst/typst
[fc]: https://github.com/tonsky/FiraCode
[cp]: https://github.com/coherentgraphics/cpdf-binaries
