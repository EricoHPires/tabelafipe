# tabelafipe
Programa que retorna preços de carros, motos ou caminhões conforme o valor informado na tabela FIPE. Elaborado durante um dos cursos da formação em Java da Alura, chamado "Java: trabalhando com lambdas, streams e Spring framework".

Para a criação, foram utilizados Java e Spring, conceitos da Programação Orientada a Objetos, listas, gerenciador de dependências Maven, consumo de API para a obtenção de dados no formato Json, com posterior utilização destes dados para criar Records, funções lambda, interfaces, biblioteca Jackson, . 

Sempre que possível, os métodos criados foram bastante genéricos, para possibilitar a sua utilização com dados de diferentes tipos; é o caso do método que converte o Json em um Record, pois ele recebe uma String no formato Json e a converte em um objeto de uma classe passada como parâmetro. O mesmo vale para o método que retorna uma lista de tipo genérico, o que permite a sua reutilização em diferentes momentos.

Também foi criado um Menu interativo que lida com possíveis exceções, como a digitação de opções inválidas por parte do usuário, instabilidade da API ou da internet, entre outras. 
