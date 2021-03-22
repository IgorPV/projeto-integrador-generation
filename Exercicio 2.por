programa
{
	
	funcao inicio()
	{
		inteiro cont, soma=0

		para (cont=1; cont<=500; cont++)
		{
			se(cont%3==0 e cont%2!=0)
			{
				soma = soma + cont
				escreva("\n",cont)
				
			}
			
		}
		escreva("\nDentro do intervalo de 1 a 500, a soma dos múltiplos de 3 ímpares é: ", soma) 
	}
}
/* $$$ Portugol Studio $$$ 
 * 
 * Esta seção do arquivo guarda informações do Portugol Studio.
 * Você pode apagá-la se estiver utilizando outro editor.
 * 
 * @POSICAO-CURSOR = 212; 
 * @PONTOS-DE-PARADA = ;
 * @SIMBOLOS-INSPECIONADOS = ;
 * @FILTRO-ARVORE-TIPOS-DE-DADO = inteiro, real, logico, cadeia, caracter, vazio;
 * @FILTRO-ARVORE-TIPOS-DE-SIMBOLO = variavel, vetor, matriz, funcao;
 */