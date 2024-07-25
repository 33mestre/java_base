@Grab(group='com.vladsch.flexmark', module='flexmark-all', version='0.62.2')
@Grab(group='com.openhtmltopdf', module='openhtmltopdf-core', version='1.0.10')
@Grab(group='com.openhtmltopdf', module='openhtmltopdf-pdfbox', version='1.0.10')

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.MutableDataSet
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import java.nio.file.Files
import java.nio.file.Paths

println("Iniciando o processo de conversão...")

def markdownFilePath = '../FUTURE.md'
def outputPdfDir = 'sys/pdf'
def outputPdfPath = "$outputPdfDir/FUTURE.pdf"

// Ler o arquivo Markdown
println("Lendo o arquivo Markdown...")
def markdownContent = new File(markdownFilePath).text

// Converter Markdown para HTML
println("Convertendo Markdown para HTML...")
def options = new MutableDataSet()
def parser = Parser.builder(options).build()
def renderer = HtmlRenderer.builder(options).build()
def htmlContent = renderer.render(parser.parse(markdownContent))

// Garantir que o diretório de saída exista
println("Verificando diretório de saída...")
def pdfDir = new File(outputPdfDir)
if (!pdfDir.exists()) {
    println("Criando diretório de saída...")
    pdfDir.mkdirs()
}

// Converter HTML para PDF e salvar no diretório de saída
println("Convertendo HTML para PDF...")
new FileOutputStream(outputPdfPath).withStream { os ->
    def builder = new PdfRendererBuilder()
    builder.useFastMode()
    builder.withHtmlContent(htmlContent, null)
    builder.toStream(os)
    builder.run()
}

println("PDF gerado com sucesso em $outputPdfPath")
