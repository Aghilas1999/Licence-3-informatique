 public class Stack {

        private Vector vector;

        public Stack(Vector vector) {
            this.vector = vector;
        }

        public void push(int value){
            vector.add(value);
        }

        public Object peek(){
            return vector.get(vector.size()-1);
        }

        public Object pop(){
            Object result = peek();
            vector.set(vector.size()-1, 0);
            return result;
        }

        public int size(){
            return vector.size();
        }

        public boolean isEmpty() {
            return vector.isEmpty();
        }
        @Override
        public String toString() {
            return "Stack{" + "Vector=" + vector+"}";
        }


    }

