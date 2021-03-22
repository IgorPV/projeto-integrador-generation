programa
{
	inclua biblioteca Matematica --> Mt
	
	funcao inicio()
	{
		real a,a2,b,b2,c,c2,d,d2
		escreva("\nDigite o primeiro número: ")
		leia(a)
		a2 = Mt.potencia(a,2.0)
		escreva("\nDigite o segundo número: ")
		leia(b)
		b2 = Mt.potencia(b,2.0)
		escreva("\nDigite o terceiro número: ")
		leia(c)
		c2 = Mt.potencia(c,2.0)
		escreva("\nDigite o quarto número: ")
		leia(d)
		d2 = Mt.potencia(d,2.0)
		
		se(c2>=1000)
		{
			escreva ("\nO quadrado do terceiro número '",c,"' é: ",c2) 
		}
		
		senao
		{	escreva("\nO quadrado do primeiro número '",a,"' é: ",a2)
			escreva("\nO quadrado do segundo número '",b,"' é: ",b2)
			escreva("\nO quadrado do terceiro número '",c,"' é: ",c2)		
			escreva("\nO quadrado do quarto número '",d,"' é: ",d2)
		}
		
		

	}
}
/* $$$ Portugol Studio $$$ 
 * 
 * Esta seção do arquivo guarda informações do Portugol Studio.
 * Você pode apagá-la se estiver utilizando outro editor.
 * 
 * @POSICAO-CURSOR = 739; 
 * @PONTOS-DE-PARADA = ;
 * @SIMBOLOS-INSPECIONADOS = ;
 * @FILTRO-ARVORE-TIPOS-DE-DADO = inteiro, real, logico, cadeia, caracter, vazio;
 * @FILTRO-ARVORE-TIPOS-DE-SIMBOLO = variavel, vetor, matriz, funcao;
 */