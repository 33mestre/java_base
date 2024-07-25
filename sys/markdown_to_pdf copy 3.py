# usar css https://github.com/develephant/mkdocs-codehilite-themes/blob/master/css/default.css

import markdown2
import weasyprint
import os
from pygments import highlight
from pygments.lexers import get_lexer_by_name
from pygments.formatters import HtmlFormatter

# Função para destacar o código com pygments
def highlight_code(code, language):
    lexer = get_lexer_by_name(language, stripall=True)
    formatter = HtmlFormatter(style='colorful', noclasses=True)  # Use 'colorful' or another style
    return highlight(code, lexer, formatter)

# Caminho para o arquivo Markdown
markdown_file_path = 'FUTURE2.md'
output_pdf_dir = './sys/pdf'
output_pdf_path = os.path.join(output_pdf_dir, 'FUTURE4.pdf')
html_output_path = './sys/FINAL4.html'  # Caminho para o arquivo HTML de depuração


# Garantir que o diretório de saída exista
if not os.path.exists(output_pdf_dir):
    os.makedirs(output_pdf_dir)

# Caminho para o arquivo CSS
css_file_path = './sys/style.css'
css_file_theme_path = './sys/css/mdTheme/default.css'

# Ler o conteúdo do arquivo CSS
with open(css_file_path, 'r', encoding='utf-8') as css_file:
    css_content = css_file.read()

# Ler o conteúdo do arquivo CSS
with open(css_file_theme_path, 'r', encoding='utf-8') as css_file_theme:
    css_content_theme = css_file_theme.read()


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

# Definir cabeçalho, rodapé e número de página como variáveis
header_content = "Shelson Ferrari"
footer_left_content = "CV Euro Pass"
footer_right_content = "Page "

# Definir estilos e conteúdo de cabeçalho e rodapé
page_styles = f"""
@page {{
  margin: 10mm;
}}
@page :first {{
  @top-center {{
    content: "{header_content}";
    font-size: 12px;
    color: #333;
  }}
  @bottom-left {{
    content: "{footer_left_content}";
    font-size: 10px;
    color: #888;
  }}
  @bottom-right {{
    content: "{footer_right_content}" counter(page);
    font-size: 10px;
    color: #888;
  }}
}}
"""

# Incluir CSS e estilos de página no HTML
html_content = f"""
<html>
<head>
<style>{css_content_theme}</style>
<style>{css_content} {page_styles}</style>
</head>
<body>{html_content}</body>
</html>
"""

# Salvar o HTML em um arquivo para depuração
with open(html_output_path, 'w', encoding='utf-8') as html_file:
    html_file.write(html_content)


# Converter HTML para PDF usando WeasyPrint
weasyprint.HTML(string=html_content).write_pdf(output_pdf_path)

print(f"PDF gerado com sucesso em {output_pdf_path}")
