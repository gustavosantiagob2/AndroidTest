🎯 Objetivo do Projeto
  O objetivo principal do projeto foi criar um aplicativo bancário de duas telas (Login e Pagamentos), utilizando Kotlin, Jetpack Compose e o padrão de arquitetura MVVM (Model-View-ViewModel),
  O projeto demonstra proficiência em consumo de APIs, gerenciamento de estado reativo e boas práticas de desenvolvimento moderno para Android.

🛠️ Tecnologias e Bibliotecas
  Categoria     :  Kotlin, MVVM",Estrutura limpa e escalável.
  Interface     :  Compose, Material Design 3.
  Assincronia   :  Kotlin Coroutines, Flow, StateFlow
  Rede          :  Retrofit 2
  Navegação     :  Navigation Compose
  Persistência  :  Jetpack DataStore
  Models        : Domain Models (Account, Payment), Data Transfer Objects (AccountDto, PaymentDto)

📁 Estrutura de Arquivos e Pacotes (Arquitetura MVVM)
  A estrutura do projeto segue o princípio de separação de responsabilidades (SRP), organizando o código em camadas bem definidas:

com.example.bancot
├── data
│   ├── dataStore           // Gerencia o Jetpack DataStore (AccountPreferences)
│   ├── remote              // Camada de API (AccountApiService, RetrofitClient)
│   │   └── model           // Data Classes de API (DTOs: AccountDto, PaymentDto)
│   └── repository          // Lógica de busca e persistência de dados (AccountRepository)
├── models                  // Domínio do Negócio
│   ├── classes             // Domain Models (Account, Payment)
│   └── routNav             // Definição das Rotas de Navegação (RoutNavigation)
├── util                    // Funções de Extensão e Conversão
│   ├── bigDecimalToCoin.kt // Ex: Conversão para formato de moeda
│   └── toDomain.kt         // Mappers de DTO para Domain Model
├── viewModels              // Camada de Lógica de UI (ViewModel)
│   ├── factory             // ViewModelFactory (para injeção de dependência manual)
│   ├── navigationScreen    // Configuração do NavHost (NavScreen)
│   └── uiState             // State Holders para a UI
│       ├── LoginScreenEvent      // Eventos de entrada da View
│       ├── LoginScreenUiState    // Estado da Tela de Login
│       ├── PaymentScreenUiState  // Estado da Tela de Pagamentos
│       ├── LoginScreenViewModel
│       └── PaymentScreenViewModel
└── views                   // Camada de UI (Jetpack Compose)
    ├── components          // Elementos reutilizáveis (Loading, ErrorText, TextFieldDefault, Skeletons)
    ├── screens             // Telas Completas (HomeScreen, PaymentsScreen)
    └── ui                  // Configurações de Tema (Color, Theme, Type)

1️⃣ Funcionalidades Chave
  1°) 
    Login (Implementado em LoginScreenViewModel e HomeScreen.kt)
    Validação Reativa: A validade do e-mail (contém "@") e da senha (min 6 caracteres, 1 letra, 1 número) é controlada reativamente no ViewModel.
    Estado do Botão: O botão de Login é habilitado apenas quando ambos os campos são válidos.
    Integração com API: Chama o endpoint de Login via AccountRepository.
    Persistência: Em caso de sucesso, armazena os dados do cliente (Nome, Conta, Agência, Saldo) no DataStore (AccountPreferences) antes de navegar.
  
  2°)
    Pagamentos (Implementado em PaymentScreenViewModel e PaymentsScreen.kt)
    Exibição de Dados        :   Carrega e exibe os dados do cliente do DataStore.
    Lista de Pagamentos      :   Busca a lista de contas pagas da API.
    Gestão de Estado Visual  :   Exibe um componente de Loading (Loading.kt) durante a chamada de rede.
    Exibe Skeletons (PaymentSkeletonItem.kt) para melhorar a experiência do usuário durante o carregamento inicial.
    Exibe Mensagem de Erro clara em caso de falha na API.

✅ Destaques de Boas Práticas
  Separação de Modelos: Uso claro de DTOs (remote/model) para a API e Domain Models (models/classes) para a camada de negócio, com mappers (toDomain.kt) para conversão.
  Gerenciamento de Estado Consistente: O uso de ...UiState e ...Event centraliza o estado e os eventos da tela, simplificando a lógica da UI (View).
  Injeção de Dependência Manual (Factory): Uso de ViewModelFactory para instanciar ViewModels, permitindo a injeção do Repositório.
  Componentização: Componentes de UI (views/components) são criados de forma modular e reutilizável, como TextFieldDefault e AccountsPayLazyColumn.

⚙️ Como Rodar o Projeto
  Pré-requisitos: Android Studio com suporte a Jetpack Compose.
  Clonar o Repositório: git clone
  Executar: Abrir o projeto no Android Studio e rodar na sua máquina virtual ou dispositivo físico.
