# DEASFIO MM - PROPOSTA 1 - SISTEMA PARA CADASTRO DE MEMBROS DO TIME MM #


<h1>Proposta do projeto</h1>
“Aqui somos um time muito unido e integrado, gostamos de nos comunicar, porém somos esquecidos. :( 

Você pode nos ajudar criando um sisteminha que permita a armazenagem do nome, email e telefone de cada pessoa do time?”



<h2>1. REQUISITOS</h2>

<h3>1.1. Requisitos funcionais</h3>

| RF    | Funcionalidade |
| ----- | -------------- |
| RF001 | O sistema deve permitir o armazenamento do nome, email e telefone de novos membros do time em um banco de dados |
| RF002 | O email, telefone e sobrenome de um membro não podem ser iguais a de outro |
| RF003 | O sistema deve permitir a exclusão dos membros do time |
| RF004 | O sistema deve permitir a atualização dos dados de membros do time |
| RF005 | O sistema deve conter validações nos campos para a inserção de novos dados |


<h3>1.2. Requisitos não funcionais</h3>
<h4>1.2.1. Usabilidade</h4>

| RF    | Funcionalidade |
| ----- | -------------- |
| RU001 | Os itens devem estar padronizados, com cores, tamanhos, fontes e etc |
| RU002 | O sistema pode possuir login |
| RU003 | O sistema pode informar o setor que cada membro se encontra |
| RU004 | O sistema pode permitir que o membro do time adicione uma mensagem para colegas na tela do seu perfil |
| RU005 | A interface pode possuir uma barra de pesquisa para facilitar a navegação |
| RU006 | O sistema pode permitir um membro do time atualizar sua mensagem em seu perfil |

<h4>1.2.2. Segurança</h4>

| RF    | Funcionalidade |
| ----- | -------------- |
| RS001 | O sistema deve permitir a gravação, exclusão e atualização de membros somente por parte do gerente |
| RS002 | O sistema deve criptografar a senha do gerente |


<h1>Diagramas</h1>

<h3>Figura 1 - Diagrama de caso de uso</h3>

![diagrama_de_caso_de_uso](https://github.com/user-attachments/assets/be789956-bcd2-4be5-924e-8ca69cb3b193)

<h3>Figura 2 - Diagrama de classes</h3>

![diagrama_de_classes](https://github.com/user-attachments/assets/467c114e-39fe-4774-946c-442255c8aad3)


<h1>Tecnologias utilizadas</h1>

| Tecnologia | Versão | Link |
| ---------- | ------ | ---- |
| Java | 21 | https://www.oracle.com/br/java/technologies/downloads/#java21 |
| SpringBoot | 3.5.0 | https://spring.io/projects/spring-boot#learn |
| Angular | 20 | https://angular.dev/overview |
| Tailwindcss | 4.1 | https://tailwindcss.com/docs/installation/using-vite |
| PostgreSQL (Ubuntu) | 16.9 | https://www.postgresql.org/download/ |


