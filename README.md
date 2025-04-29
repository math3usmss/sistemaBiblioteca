## Sistema de Emprestimo biblioteca

### Sobre o projeto
- Sistema de criação de Emprestimo(Livro, Revista, Artigo Científico) para biblioteca.
- Aplicativo feito para uso no console do java.
  
- A aplicação possui as funcionalidades de:
   - Criar um material (Livro, Revista, Artigo Científico).
   - Listagem de todos os materiais cadastrados.
   - Listagem de todos os materiais disponíveis para emprestimo.
   - Buscar material por titulo.
   - Cadastrar usuário.
   - Fazer um emprestimo.
   - Buscar Emprestimos
   - Fazer devolução.
   - Salvamento automático dos usuarios, emprestimos e materiais em arquivo ao sair da aplicação, junto com a instanciação automática ao entrar novamente.
   - Cálculo automático de multa, a depender do tipo de material.

- Técnico
  - Projeto feito em Java, separado por camadas:
    - Models, representando as entidades do sistema, a classe abstrata Material e as subclasses derivadas, Livro, Revista e Artigo científico.
    - Services, responsável pela lógica de negócio da aplicação, funcionalidades básicas, métodos para implementação correta a depender do tipo de material,  etc.
 
## Autor
- Matheus Manoel Souza da Silva
- https://www.linkedin.com/in/matheus-manoel-92b578220/
