
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoPerecivel extends Produto {
    private static final double DESCONTO = 0.25;
    private static final int PRAZO_DESCONTO = 7;
    private LocalDate dataValidade;

    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate validade) {
        super(desc, precoCusto, margemLucro);

        if (validade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data não pode ser anterior a data atual!");
        }
        this.dataValidade = validade;
    }

    @Override
    public double valorDeVenda() {
        double desconto = 0d;
        long days = LocalDate.now().until(dataValidade).getDays();

        if (days <= PRAZO_DESCONTO) {
            desconto = DESCONTO;
        }

        return (precoCusto * (1.0 + margemLucro) * (1 - desconto));
    }

    @Override
    public String toString() {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dados = super.toString();
        dados += "\n Válido até " + formato.format(dataValidade);

        return dados;
    }

    /**
     * Gera uma linha de texto a partir dos dados do produto. Preço e margem de
     * lucro vão formatados com 2 casas
     * decimais.
     * Data de validade vai no formato dd/mm/aaaa
     * 
     * @return Uma string no formato "2;
     *         descrição;preçoDeCusto;margemDeLucro;dataDeValidade"
     */
    @Override
    public String gerarDadosTexto() {
         /*
         * Você deve implementar aqui a lógica que monta a String com os atributos do
         * objeto ProdutoPerecivel,
         * respeitando o formato do arquivo de dados.
         */

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String precoFormatado = String.format("%.2f", precoCusto).replace(",", ".");
        String margemFormatada = String.format("%.2f", margemLucro).replace(",", ".");
        String dataFormatada = formatter.format(this.dataValidade);

       

        return String.format("2;%s;%s;%s", super.descricao, precoFormatado, margemFormatada, dataFormatada);
    }
}
