import re
import os

def adjust_markdown_table(input_file, output_file):
    with open(input_file, 'r', encoding='utf-8') as file:
        lines = file.readlines()

    table_start = False
    adjusted_lines = []
    for line in lines:
        if '| Name |' in line:  # Iniciar o cabeçalho corretamente
            adjusted_lines.append('| Name | Description |\n')
            adjusted_lines.append('|---|---|\n')
            table_start = True
        elif table_start and '|---|' in line:  # Ajustar o divisor de colunas
            continue  # Ignorar a linha do divisor antigo
        elif table_start:
            # Processar as linhas da tabela
            match = re.match(r'\|\s*\[(.*)\]\((.*)\)\s*\|\s*(.*)\|?', line)
            if match:
                name = match.group(1)
                link = match.group(2)
                description = match.group(3).strip() if match.group(3) else ''
                adjusted_line = f"| [{name}]({link}) | {description} |\n"
                adjusted_lines.append(adjusted_line)
            else:
                # Se a linha não corresponde, verificar se é o final da tabela
                if line.strip() == '':
                    table_start = False
                adjusted_lines.append(line)
        else:
            adjusted_lines.append(line)

    # Salvar conteúdo ajustado no arquivo de saída
    with open(output_file, 'w', encoding='utf-8') as file:
        file.writelines(adjusted_lines)
    print(f"Markdown table adjusted and saved to {output_file}")

# Caminho para o diretório dos documentos
docs_path = 'docs/site'
input_md = os.path.join(docs_path, 'index.md')
temp_md = os.path.join(docs_path, 'index2.md')

# Renomear index.md para index2.md
os.rename(input_md, temp_md)
print(f"Renamed {input_md} to {temp_md}")

# Ajustar o conteúdo do arquivo renomeado e salvar como index.md
adjust_markdown_table(temp_md, input_md)

# Excluir o arquivo temporário index2.md
os.remove(temp_md)
print(f"Deleted temporary file {temp_md}")



