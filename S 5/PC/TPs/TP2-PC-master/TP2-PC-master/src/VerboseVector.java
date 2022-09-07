/** cette classe cert a factoreser le code de la classe vector , en va hérité de la classe
  de la classe vector.
 */
public class VerboseVector extends Vector{
    @Override
    public void add(Object element) {
        super.add(element);
        System.out.println(element.toString());
    }
    @Override
    public void set(int index, Object element) {
        super.set(index,element);
        System.out.println(index +","+element);

    }
    @Override
    public Object get(int index) {
        System.out.println("get(" +index+")");
        return super.get(index);
    }


}
