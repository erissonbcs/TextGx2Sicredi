# TextGx2Sicredi

![imgs_poc](https://user-images.githubusercontent.com/7661063/149450640-0e8ab98a-0eb6-4ad6-abea-f76706558488.png)

# Arquitetura utilizada - MVVM

Na arquitetura MVVM você tem uma visão da clara separação da Interface com o usuário( View ), sua lógica de apresentação (ViewModel) e os seus Dados(Model). 
E trabalhando desta forma, temos separação de responsabilidades, desacoplamento e conseguimos evoluir e manter melhor as nossas aplicações

# Frameworks e Libraries

Retrofit - Foi utilizado para consumo da API Rest. A pricipal vantagem é que ele modela os endpoints REST como interfaces Java, 
tornando-os muito simples de entender e consumir.

Glide - Utilzado para carregar e exibir imagens de muitas fontes, ao mesmo tempo em que cuida do cache e mantém um baixo impacto na memória ao 
fazer manipulações de imagens

Material Design -  Foi Criada pela Google para padronizar todas as suas interfaces gráficas. Sua Utilização foi devido a pra proporcionar uma melhor 
experiência po usuário.

Databinding - Utilizado para facilitar a comunicação entre dados e interface gráfica.

LiveData - Usada para observar as alterações de um ViewModel e atualizar essas alterações. O LiveData ajuda a reduzir vazamentos de memória e manipulação 
manual do ciclo de vida. Também garante que a interface do usuário esteja sempre atualizada com os dados, mesmo quando a atividade do aplicativo 
for reiniciada durante o uso. 

Google Maps SDK - Utilzido para exibir o mapa na aplicação.
