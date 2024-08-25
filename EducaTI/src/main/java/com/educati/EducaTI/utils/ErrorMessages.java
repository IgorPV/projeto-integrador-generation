package com.educati.EducaTI.utils;

public enum ErrorMessages {
   LOGIN_ERROR("Erro: Não é possivel efetuar o login, verifique e-mail e senha."),
   USER_ALREADY_EXISTS("Erro: O usuário que está tentando criar já existe."),
   ACCOUNT_NOT_FOUND("Erro: Conta não existe."),
   USER_OR_THEME_NOT_FOUND("Erro: Usuario e/ou tema não cadastrado!");

   private final String description;

   ErrorMessages(String description){
       this.description = description;
   }

   public String getDescription() {
       return description;
   }
}
