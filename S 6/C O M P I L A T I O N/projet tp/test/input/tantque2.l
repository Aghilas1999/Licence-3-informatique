main()
  entier a, entier b;
  {
  a = 0;
  tantque (a < 4) faire {
    b = 0;
    tantque (b < 4) faire {
      ecrire(a);
      ecrire(b);
      b = b + 1;
    }
    a = a + 1;
  }
}
