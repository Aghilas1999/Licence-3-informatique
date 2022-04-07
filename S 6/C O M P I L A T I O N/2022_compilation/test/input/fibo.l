fibo( entier n ) 
{
  si n < 2 alors {
    retour 1;
  }
  sinon {
    retour fibo( n - 1 ) + fibo( n - 2 );
  }
}

main()
{
  ecrire( fibo( 9 ) );
}
