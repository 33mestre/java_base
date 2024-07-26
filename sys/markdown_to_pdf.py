# ---------------------------------
#    _____ __         __                    ______                          _
#   / ___// /_  ___  / /________  ____     / ____/__  ______________ ______(_)
#   \__ \/ __ \/ _ \/ / ___/ __ \/ __ \   / /_  / _ \/ ___/ ___/ __ `/ ___/ /
#  ___/ / / / /  __/ (__  ) /_/ / / / /  / __/ /  __/ /  / /  / /_/ / /  / /
# /____/_/ /_/\___/_/____/\____/_/ /_/  /_/    \___/_/  /_/   \__,_/_/  /_/
# ---------------------------------
# Currency Conversion API
# Python Script 
# Generates PDF File from Markdown File, coloring block codes
# @see https://github.com/develephant/mkdocs-codehilite-themes/blob/master/css/default.css
# use exiftool to extract metatags after generations
# ---------------------------------

import markdown2
import weasyprint
import os
from pygments import highlight
from pygments.lexers import get_lexer_by_name
from pygments.formatters import HtmlFormatter
from datetime import datetime
from PyPDF2 import PdfReader, PdfWriter

# ---------------------------------
# Add Metatag to PDF file
# ---------------------------------
def add_metadata(input_pdf, output_pdf, metadata):
    pdf_reader = PdfReader(input_pdf)
    pdf_writer = PdfWriter()
    
    # ---------------------------------
    # Add all pages to the PDF writer
    # ---------------------------------
    for page_num in range(len(pdf_reader.pages)):
        pdf_writer.add_page(pdf_reader.pages[page_num])
    
    # ---------------------------------
    # Add metadata
    # ---------------------------------
    pdf_writer.add_metadata(metadata)
    
    # ---------------------------------
    # Write the output file
    # ---------------------------------
    with open(output_pdf, 'wb') as out_pdf:
        pdf_writer.write(out_pdf)

# ---------------------------------
# Function to highlight code with pygments
# ---------------------------------
def highlight_code(code, language):
    lexer = get_lexer_by_name(language, stripall=True)
    formatter = HtmlFormatter(style='colorful', noclasses=True)  # Use 'colorful' or another style
    return highlight(code, lexer, formatter)

# ---------------------------------
# Array of objects with file information
# ---------------------------------
documents = [
    {
        "nome": "SHELSON_FERRARI_CV_en",
        "header_content": "Shelson Ferrari - Full Stack Software Engineer | Cloud Architect",
        "footer_left_content": "CV Euro Pass - en version",
        "footer_right_content": "Page ",
        "author": "Shelson Ferrari",
        "version": "1.0"
    },
    {
        "nome": "SHELSON_FERRARI_CV_pt_br",
        "header_content": "Shelson Ferrari - Engenheiro de Software Full Stack | Arquiteto de Nuvem",
        "footer_left_content": "CV Euro Pass - versão pt_br",
        "footer_right_content": "Página ",
        "author": "Shelson Ferrari",
        "version": "1.0"
    },
    {
        "nome": "SHELSON_FERRARI_CV_es",
        "header_content": "Shelson Ferrari - Ingeniero de Software Full Stack | Arquitecto de Nube",
        "footer_left_content": "CV Euro Pass - versión es",
        "footer_right_content": "Página ",
        "author": "Shelson Ferrari",
        "version": "1.0"
    }
]

# ---------------------------------
# Paths for input and output directories
# ---------------------------------
input_md_dir = './cv/'
output_pdf_dir = './pdf'

if not os.path.exists(output_pdf_dir):
    os.makedirs(output_pdf_dir)

# ---------------------------------
# Paths for CSS files
# ---------------------------------
css_file_path = './style.css'
css_file_theme_path = './css/mdTheme/default.css'

# ---------------------------------
# Read CSS file contents
# ---------------------------------
with open(css_file_path, 'r', encoding='utf-8') as css_file:
    css_content = css_file.read()

with open(css_file_theme_path, 'r', encoding='utf-8') as css_file_theme:
    css_content_theme = css_file_theme.read()

# ---------------------------------
# Function to generate the PDF
# ---------------------------------
def gerar_pdf(document):

    # ---------------------------------
    # Path for the Markdown file
    # ---------------------------------
    markdown_file_path = os.path.join(input_md_dir, f"{document['nome']}.md")
    output_pdf_path = os.path.join(output_pdf_dir, f"{document['nome']}.pdf")

    # ---------------------------------
    # Read the Markdown file
    # ---------------------------------
    with open(markdown_file_path, 'r', encoding='utf-8') as markdown_file:
        markdown_content = markdown_file.read()

    # ---------------------------------
    # Convert Markdown to HTML with support for tables and code blocks
    # ---------------------------------
    html_content = markdown2.markdown(markdown_content, extras=["tables", "fenced-code-blocks"])

    # ---------------------------------
    # Add syntax highlighting
    # ---------------------------------
    html_content_lines = html_content.split('\n')
    for i, line in enumerate(html_content_lines):
        if line.startswith('<pre><code class="'):
            lang = line.split('"')[1].split('-')[1]
            code = '\n'.join(html_content_lines[i+1:i+2]).strip()
            highlighted_code = highlight_code(code, lang)
            html_content_lines[i] = f'<pre><code class="{lang}">{highlighted_code}</code></pre>'

    html_content = '\n'.join(html_content_lines)

#   @top-right {{
#         content: '';
#         content: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAUCAMAAACtdX32AAAAIGNIUk0AAHomAACAhAAA+gAAAIDoAAB1MAAA6mAAADqYAAAXcJy6UTwAAADMUExURQNGlQBElABDlQFElDFdegxKj1NvaCJVg3aCU3WBVCNWggtJjxNOipWSQz5kcxBMjA9MjD9kcpSSQxNNiwlJjxZPiQNFkwxKjgZHkQBElU5taZaTQJeTQE1sah9UhDxjdAVGkgVHkTxjcx5ThAJFkwBCli5cfHmDUhROii1bfS1bfHiDUhFNixJNiyxbfQFFkz5kck5sag5LjQ9LjJeUQExsagRGkhVOipSRQz9kcxFMjEBlcgpJj1FuaSFVg3iDU3eCUyJVgjBde////wzxLiAAAAABYktHRENn0A1iAAAAB3RJTUUH6AcaAwk30H74uwAAAM9JREFUKM+FkucWgjAMhUmKOFDBwVARFwhuxYUD1/s/lHqUgy2o+ZWcr7e9ScpxvwOoQKRrjmKE5wl14gOnhDTJZElOEJMw5gtFlGQslSsYx1VF1XTAGuiaqlRZjPWG/BahZDSRxWarHUo63R6LwbIjQ7bFXI59JzKErjNAGg9H4whPnCmNYQYf00Ag7NtmUQ9Tfd6LNbYwvLCx5Wodcy66qrd5JhtPdUVW/RBt/R3uJTz4bsJQAY7BiZwv5BocvywUgL8BfFnoa+V0/ecv3QHwgw6Bi4GI+QAAACV0RVh0ZGF0ZTpjcmVhdGUAMjAyNC0wNy0yNlQwMzowOTozOSswMDowMMdmgq4AAAAldEVYdGRhdGU6bW9kaWZ5ADIwMjQtMDctMjZUMDM6MDk6MzkrMDA6MDC2OzoSAAAAKHRFWHRkYXRlOnRpbWVzdGFtcAAyMDI0LTA3LTI2VDAzOjA5OjU1KzAwOjAw4OF4PgAAAABJRU5ErkJggg==');
#         width: 30px;
#         height: 20px;
#         padding-right: 12px;
#         }}

    # ---------------------------------
    # Define styles and header/footer content
    # ---------------------------------
    page_styles = f"""
    @page {{
      margin: 10mm;
    }}
    @page:first {{
      @top-center {{
        margin-top: 14px;
      }}

     
    }}

    @page {{
      @top-center {{
        text-transform: normal;
        content: "{document['header_content']}";
        font-size: 13px;
        color: #333;
        font-family: 'Lato', 'Fira Code', Consolas,  Courier, monospace;
      }}
      @bottom-left {{
        text-transform: uppercase;
        content: "{document['footer_left_content']}";
        font-size: 12px;
        color: #333;
        padding-left: 20px;
        font-family: 'Fira Code', Consolas,  Courier, monospace;
      }}
      @bottom-right {{
        text-transform: uppercase;
        content: "{document['footer_right_content']}" counter(page) " / " counter(pages);
        font-size: 12px;
        color: #333;
        padding-right: 20px;
        font-family: 'Fira Code', Consolas,  Courier, monospace;
      }}
    }}
    """

    # ---------------------------------
    # Include CSS and page styles in HTML
    # ---------------------------------
    html_content = f"""
    <html>
    <head>
    <meta name="author" content="{document['author']}">
    <meta name="Producer" content="{document['author']}">
    <meta name="version" content="{document['version']}">
    <meta name="title" content="{document['header_content']}">
    <meta name="date" content="{datetime.now().strftime('%Y-%m-%d')}">
    
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Raleway:wght@400;500;600&display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Libre+Franklin:wght@400;500;600&display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Fira+Code:wght@400;700&display=swap">

    <style>{css_content} {page_styles}</style>
    </head>
    <body>{html_content}</body>
    </html>
    """

    # ---------------------------------
    # Convert HTML to PDF using WeasyPrint
    # ---------------------------------
    weasyprint.HTML(string=html_content).write_pdf(output_pdf_path)

    print(f"PDF gerado com sucesso em {output_pdf_path}")

    # ---------------------------------
    # Metadata to add to the PDF
    # ---------------------------------
    metadata = {
        '/Author': document['author'],
        '/Title': document['header_content'],
        '/Subject': document['footer_left_content'],
        '/Keywords': 'Shelson, Ferrari, Kadosch, Java, Python, CV, GO, Node.js, JavaScript, AWS, GCP, IBM',
        '/Producer': document['author'],
        '/CreationDate': datetime.now().strftime("D:%Y%m%d%H%M%S")
    }

    add_metadata(output_pdf_path, output_pdf_path, metadata)

# ---------------------------------
# Generate PDFs for each document
# ---------------------------------
for doc in documents:
    gerar_pdf(doc)
