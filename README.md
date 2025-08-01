<h1>CEP Backend API</h1>
<p></p>
<hr/>

<p>API REST desenvolvida em Java 21 com Spring Boot 3 para cadastro de usuários e consulta de endereços via ViaCEP. O projeto possui camadas bem definidas — application, domain, infrastructure e presentation.</p>

<h2>💡 Tecnologias utilizadas</h2>

<h3>Java 21 + Spring Boot 3</h3>
<ul>
  <li><strong>Spring Boot</strong>: framework robusto e produtivo para aplicações Java modernas.</li>
  <li><strong>Spring Web</strong>: para criação de endpoints REST.</li>
  <li><strong>Spring Data JPA e Hibernate</strong>: para persistência de dados.</li>
  <li><strong>Spring Validation</strong>: para validação de inputs.</li>
  <li><strong>H2 Database</strong>: banco de dados em memória para desenvolvimento.</li>
  <li><strong>SpringDoc OpenAPI (Swagger)</strong>: para documentação da API.</li>
  <li><strong>Maven</strong>: gerenciamento de dependências.</li>
</ul>

<h2>🏗️ Arquitetura</h2>

<p>O código é organizado por responsabilidades:</p>

<pre>
├── application      # DTOs, serviços e portas
├── domain           # Entidades, exceptions e validações
├── infrastructure   # Implementações de repositório e integrações externas
└── presentation     # Controllers e handlers
</pre>

<h2>🚀 Como Executar</h2>

<h3>Pré-requisitos</h3>
<ul>
  <li>Java 21</li>
  <li>Maven 3.6+</li>
</ul>

<h3>Executando a aplicação</h3>

<ol>
  <li><strong>Clone o repositório</strong>
    <pre>git clone https://github.com/felipelago/CepBackend.git
cd CepBackend</pre>
  </li>
  
  <li><strong>Execute com Maven</strong>
    <pre>./mvnw spring-boot:run</pre>
  </li>
  
  <li><strong>Acesse a documentação da API</strong>
    <pre>http://localhost:8080/swagger-ui.html</pre>
  </li>
</ol>

<h3>Banco de Dados</h3>
<ul>
  <li>A aplicação utiliza <strong>H2 Database</strong> em memória</li>
  <li>Console H2: <code>http://localhost:8080/h2-console</code></li>
  <li>JDBC URL: <code>jdbc:h2:mem:testdb</code></li>
  <li>Username: <code>sa</code></li>
  <li>Password: <code>password</code></li>
</ul>

<h2>📋 Endpoints Principais</h2>

<h3>ViaCEP</h3>
<ul>
  <li><code>GET /api/v1/viaCep/{cep}</code># Consulta endereço pelo CEP</li>
</ul>

<h3>Usuarios</h3>
<ul>
  <li><code>POST   /api/v1/usuarios</code> # Cadastrar usuário</li>
  <li><code>GET    /api/v1/usuarios/listar-usuarios</code> # Listar usuários</li>
  <li><code>GET    /api/v1/usuarios/listar-enderecos</code> # Listar endereços</li>
  <li><code>PUT    /api/v1/usuarios/{id}</code> # Atualizar usuário</li>
  <li><code>DELETE /api/v1/usuarios/{id}</code> # Remover usuário</li>
</ul>

<h2>🧪 Exemplo de Request</h2>

<h3>Cadastrar Usuario</h3>

<pre>{
  "nome": "Jon Doe",
  "cpf": "12345678901",
  "cep": "01001000",
  "logradouro": "Rua Exemplo",
  "bairro": "Centro",
  "cidade": "São Paulo",
  "estado": "SP"
}</pre>

<h2>🛡️ Validações e Tratamento de Erros</h2>

<ul>
  <li><strong>Validação de entrada</strong>: Bean Validation com anotações</li>
  <li><strong>Tratamento global</strong>: <code>@RestControllerAdvice</code> para captura de exceções</li>
  <li><strong>Códigos HTTP apropriados</strong>: 400, 404, 409, 500</li>
  <li><strong>Mensagens padronizadas</strong>: Responses de erro estruturados</li>
</ul>

<h2>📦 Build e Deploy</h2>

<h3>Gerar JAR</h3>
<pre>./mvnw clean package</pre>

<h3>Executar JAR</h3>
<pre>java -jar target/cepbackend-0.0.2-SNAPSHOT.jar</pre>

<h2>📝 Próximos Passos</h2>

<ul>
  <li>[ ] Adicionar testes unitários e de integração</li>
  <li>[ ] Implementar paginação nas listagens</li>
  <li>[ ] Melhorar documentação da API</li>
  <li>[ ] Explorar integração com banco PostgreSQL para ambientes de produção</li>
  <li>[ ] Criar camada de segurança utilizando JWT</li>
</ul>
