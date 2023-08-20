# Gerenciamento de Produtos

Este é um projeto em Java que oferece funcionalidades de CRUD (Create, Read, Update, Delete) para produtos, permitindo a interação com o usuário por meio do terminal com log de eventos em arquivo TXT.

## Funcionalidades

- **Criar Produto:** ✅

- **Buscar Produto:** ✅ 


- **Alterar Produto:** ✅

- **Deletar Produto:** ✅

- **Exibir Produto:** ✅

## Camadas do Projeto

- **`controle`**: `Controller` concreto herdando de interface que disponibiliza os métodos do CRUD para Produtos.

- **`entidades`**: Este pacote contém a classe `Produto` com sua estrutura e `toString` para impressão no arquivo TXT

- **`enums`**: Este pacote contém as enumerações utilizadas no projeto, como `Categoria` e `OpcoesCrud` para representar categorias de produtos e opções do CRUD, respectivamente.

- **`servicos`**: `CrudProdutoService` implementando `CrudService`, o que desacopla o sistema, tornando-o aberto para novos tipos de gerenciadores. No momento, é permitido somente um `CrudService` instanciado, com o uso do Design Pattern Singleton

- **`exceptions`**: Classes que gerenciam possíveis exceções no programa, cada uma em sua devida camada

## Amostra de Evento no TXT
![image](https://github.com/RayanArgolo03/crud-produtos-console-java/assets/113947677/ab6fbddf-5086-4ed3-b186-538dea781370)

## Considerações Finais e aprendizados

Este projeto é um exemplo simples de um sistema de gerenciamento de produtos com funcionalidades básicas de CRUD, em que utilizei os seguintes conceitos:  Generics, Arquitetura de Camadas (Services, Entities, Controllers, Exceptions, Enums), uso da interface Comparator 🚧, leitura e escrita em arquivos e o Design Pattern Singleton para instancias únicas.
