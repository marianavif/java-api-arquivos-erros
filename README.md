# Projeto Final - Java: consumindo API, gravando arquivos e lidando com erros da Alura

***

![Static Badge](https://img.shields.io/badge/Status-Done-dark_green)

*Projeto Final do curso Java: consumindo API, gravando arquivos e lidando com erros da Alura*

### Consulta da API ViaCEP com gravação de arquivo .JSON

Consulte os dados acerca de quantos locais desejar a partir de seu CEP, utilizando a API *ViaCEP*.
Imprima os dados dos endereços pesquisados na tela, gerando o arquivo **pesquisaCep.json** contendo-os.

### Pacotes

- #### modelos

  - #### *ErroDeNumeroDeDigitosException*
    
     Exceção que deve ser lançada quando houver discrepância entre o número de dígitos desejado e o efetivamente digitado.

  - #### *EnderecoCEP*

     Registro utilizado para construir um objeto com atributos CEP, logradouro, complemento, bairro, localidade e UF, semelhantes
     aos dados contidos no arquivo .json provido pela API *ViaCEP*.

  - #### *Endereco*
    
     Classe que cria um objeto com atributos correspondentes a CEP, lugar, complemento, bairro, cidade e UF. Possui construtor caracterizado 
     apenas pelo atributo CEP; possui também construtor a partir do registro do tipo **EnderecoCEP**. Possui *getters* e *setters* de todos seus 
     atributos. Há sobrescrita do método **toString()**, formando uma string com seus atributos.

- #### conexao

  - #### Conexao
  
     Classe cujo único atributo é a URL de conexão da API. O valor da url é determinado pelo parâmetro de valor inteiro de seu método **setUrl()**.
     O método **conectar()** estabelece uma conexão com a API através do endereço contido em seu atributo *url* e retorna a resposta do servidor.

- #### principal

  - #### Principal
     
      Cria o objeto do tipo *Scanner* para leitura do teclado. Inicializa a lista **locaisPesquisados** do tipo *Endereco* para armazenar os dados 
      obtidos das pesquisas. Cria um objeto do tipo *Gson*, a partir do qual será manipulado o arquivo **.JSON** que a API enviar.
      Em loop, pede ao usuário que digite 8 dígitos que corresponderão ao CEP a pesquisar. Lança a exceção *ErroDeNumeroDeDigitosException* caso o
      número de dígitos não seja 8. Também lança exceção caso o tipo de dado de entrada não seja inteiro (*InputMismatchException*). Caso tudo esteja
      de acordo, cria um objeto do tipo *Conexao* e passa o parâmetro CEP digitado para seu método **setURL()**.
      
      Assim, cria uma *String* que receberá a resposta do servidor após a execução do método **conectar()**.
      É criado o registro do tipo *EnderecoCEP* a partir da "tradução" da String em formato **JSON** para este tipo de objeto.
      Então, é criado o objeto do tipo *Endereco* a partir do registro do tipo *EnderecoCEP* anteriormente criado. Este objeto do tipo *Endereco* é
      adicionado à lista **locaisPesquisados**.
    
      O loop se encerra quando o programa pergunta se é desejado continuar e, em resposta, é digitado o inteiro 1.
      Após o fim do loop, é impresso na tela o conteúdo da lista **locaisPesquisados** e o arquivo **pesquisaCEP.json** no formato **.JSON** é criado, contendo estas
      informações.