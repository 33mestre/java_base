# import markdown
# import pdfkit
# import os
# from pygments import highlight
# from pygments.lexers import get_lexer_by_name
# from pygments.formatters import HtmlFormatter

# # Função para destacar o código com pygments
# def highlight_code(code, language):
#     lexer = get_lexer_by_name(language, stripall=True)
#     formatter = HtmlFormatter(style='colorful', noclasses=True)
#     return highlight(code, lexer, formatter)

# # Caminho para o arquivo Markdown
# markdown_file_path = 'FUTURE2.md'
# output_pdf_dir = './sys/pdf'
# output_pdf_path = os.path.join(output_pdf_dir, 'FUTURE3.pdf')

# # Garantir que o diretório de saída exista
# if not os.path.exists(output_pdf_dir):
#     os.makedirs(output_pdf_dir)

# # Ler o arquivo Markdown
# with open(markdown_file_path, 'r', encoding='utf-8') as markdown_file:
#     markdown_content = markdown_file.read()

# # Converter Markdown para HTML com suporte a tabelas
# html_content = markdown.markdown(markdown_content, extensions=['fenced_code', 'codehilite', 'tables'])

# # Adicionar destaque de sintaxe
# html_content_lines = html_content.split('\n')
# for i, line in enumerate(html_content_lines):
#     if line.startswith('<pre><code class="'):
#         lang = line.split('"')[1].split('-')[1]
#         code = '\n'.join(html_content_lines[i+1:i+2]).strip()
#         highlighted_code = highlight_code(code, lang)
#         html_content_lines[i] = f'<pre><code class="{lang}">{highlighted_code}</code></pre>'

# html_content = '\n'.join(html_content_lines)

# # Adicionar CSS básico para o PDF
# css_content = """
# body {
#   font-family: Arial, sans-serif;
#   line-height: 1.6;
#   padding: 10px;
# }
# pre {
#   background-color: #f4f4f4;
#   padding: 10px;
#   border-radius: 4px;
#   overflow: auto;
# }
# code {
#   background-color: #f4f4f4;
#   padding: 2px 4px;
#   border-radius: 4px;
# }
# """

# # Incluir CSS e estilo no HTML
# html_content = f"""
# <html>
# <head>
# <style>{css_content}</style>
# </head>
# <body>
# {html_content}
# </body>
# </html>
# """

# # Converter HTML para PDF usando pdfkit
# pdfkit.from_string(html_content, output_pdf_path)

# print(f"PDF gerado com sucesso em {output_pdf_path}")


import markdown2
import weasyprint
import os
from pygments import highlight
from pygments.lexers import get_lexer_by_name
from pygments.formatters import HtmlFormatter

# Função para destacar o código com pygments
def highlight_code(code, language):
    lexer = get_lexer_by_name(language, stripall=True)
    formatter = HtmlFormatter(nowrap=True)
    return highlight(code, lexer, formatter)

# Caminho para o arquivo Markdown
markdown_file_path = 'FUTURE2.md'
output_pdf_dir = './sys/pdf'
output_pdf_path = os.path.join(output_pdf_dir, 'FUTURE3.pdf')

# Garantir que o diretório de saída exista
print("Verificando diretório de saída...")
if not os.path.exists(output_pdf_dir):
    print("Criando diretório de saída...")
    os.makedirs(output_pdf_dir)

# Caminho para o arquivo CSS
css_file_path = './sys/style.css'

# Ler o conteúdo do arquivo CSS
print("Lendo o arquivo CSS...")
with open(css_file_path, 'r', encoding='utf-8') as css_file:
    css_content = css_file.read()

# Ler o arquivo Markdown
print("Lendo o arquivo Markdown...")
with open(markdown_file_path, 'r', encoding='utf-8') as markdown_file:
    markdown_content = markdown_file.read()

# Converter Markdown para HTML com suporte a tabelas
print("Convertendo Markdown para HTML...")
html_content = markdown2.markdown(markdown_content, extras=["tables", "fenced-code-blocks"])

# Adicionar destaque de sintaxe
print("Adicionando destaque de sintaxe...")
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
<style>{css_content} {page_styles}</style>
</head>
<body>{html_content}</body>
</html>
"""

# Converter HTML para PDF usando WeasyPrint
print("Convertendo HTML para PDF...")
weasyprint.HTML(string=html_content).write_pdf(output_pdf_path)

print(f"PDF gerado com sucesso em {output_pdf_path}")



