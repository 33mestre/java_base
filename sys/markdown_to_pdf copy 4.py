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
# ---------------------------------

import markdown2
import weasyprint
import os
from pygments import highlight
from pygments.lexers import get_lexer_by_name
from pygments.formatters import HtmlFormatter

# Função para destacar o código com pygments
def highlight_code(code, language):
    lexer = get_lexer_by_name(language, stripall=True)
    formatter = HtmlFormatter(style='colorful', noclasses=True)  # Use 'colorful' ou outro estilo
    return highlight(code, lexer, formatter)

# Array de objetos com as informações dos arquivos
documents = [
    {
        "nome": "SHELSON_FERRARI_CV_en",
        "header_content": "Shelson Ferrari - Full Stack Software Engineer | Cloud Architect",
        "footer_left_content": "CV Euro Pass - en version",
        "footer_right_content": "Page "
    },
    {
        "nome": "SHELSON_FERRARI_CV_pt_br",
        "header_content": "Shelson Ferrari - Engenheiro de Software Full Stack | Arquiteto de Nuvem",
        "footer_left_content": "CV Euro Pass - versão pt_br",
        "footer_right_content": "Página "
    },
    {
        "nome": "SHELSON_FERRARI_CV_es",
        "header_content": "Shelson Ferrari - Ingeniero de Software Full Stack | Arquitecto de Nube",
        "footer_left_content": "CV Euro Pass - versión es",
        "footer_right_content": "Página "
    }
]

# Caminho para os diretórios de entrada e saída
# input_md_dir = './sys'
# output_pdf_dir = './sys/pdf'

input_md_dir = './'
output_pdf_dir = './pdf'


if not os.path.exists(output_pdf_dir):
    os.makedirs(output_pdf_dir)

# Caminho para os arquivos CSS
# css_file_path = './sys/style.css'
# css_file_theme_path = './sys/css/mdTheme/default.css'

css_file_path = './style.css'
css_file_theme_path = './css/mdTheme/default.css'

# Ler o conteúdo dos arquivos CSS
with open(css_file_path, 'r', encoding='utf-8') as css_file:
    css_content = css_file.read()

with open(css_file_theme_path, 'r', encoding='utf-8') as css_file_theme:
    css_content_theme = css_file_theme.read()

# Função para gerar o PDF
def gerar_pdf(document):
    # Caminho para o arquivo Markdown
    markdown_file_path = os.path.join(input_md_dir, f"{document['nome']}.md")
    output_pdf_path = os.path.join(output_pdf_dir, f"{document['nome']}.pdf")

    # Ler o arquivo Markdown
    with open(markdown_file_path, 'r', encoding='utf-8') as markdown_file:
        markdown_content = markdown_file.read()

    # Converter Markdown para HTML com suporte a tabelas e blocos de código
    html_content = markdown2.markdown(markdown_content, extras=["tables", "fenced-code-blocks"])

    # Adicionar destaque de sintaxe
    html_content_lines = html_content.split('\n')
    for i, line in enumerate(html_content_lines):
        if line.startswith('<pre><code class="'):
            lang = line.split('"')[1].split('-')[1]
            code = '\n'.join(html_content_lines[i+1:i+2]).strip()
            highlighted_code = highlight_code(code, lang)
            html_content_lines[i] = f'<pre><code class="{lang}">{highlighted_code}</code></pre>'

    html_content = '\n'.join(html_content_lines)

    # Definir estilos e conteúdo de cabeçalho e rodapé
    page_styles = f"""
    @page {{
      margin: 10mm;
    }}
    @page {{
      @top-center {{
        text-transform: uppercase;
        content: "{document['header_content']}";
        font-size: 12px;
        color: #333;
        font-family: 'Fira Code', Consolas, "Liberation Mono", Menlo, Courier, monospace;
      }}
      @bottom-left {{
        text-transform: uppercase;
        content: "{document['footer_left_content']}";
        font-size: 10px;
        color: #888;
        padding-left: 20px;
        font-family: 'Fira Code', Consolas, "Liberation Mono", Menlo, Courier, monospace;
      }}
      @bottom-right {{
        text-transform: uppercase;
        content: "{document['footer_right_content']}" counter(page);
        font-size: 12px;
        color: #333;
        padding-right: 20px;
        font-family: 'Fira Code', Consolas, "Liberation Mono", Menlo, Courier, monospace;
      }}
    }}
    """

    # Incluir CSS e estilos de página no HTML
    html_content = f"""
    <html>
    <head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Fira+Code:wght@400;700&display=swap">
    <style>{css_content} {page_styles}</style>
    </head>
    <body>{html_content}</body>
    </html>
    """

    # Converter HTML para PDF usando WeasyPrint
    weasyprint.HTML(string=html_content).write_pdf(output_pdf_path)

    print(f"PDF gerado com sucesso em {output_pdf_path}")

# Gerar PDFs para cada documento
for doc in documents:
    gerar_pdf(doc)
