import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void push() {
        Vector vector = new Vector();
        Stack stack = new Stack(new Vector());
        stack.push(1);
        assertThat(stack.peek(), equalTo(1));
    }

    @Test
    public void peek() {
    }

    @Test
    public void pop() {
    }

    @Test
    public void size() {
    }

    @Test
    public void isEmpty() {
    }
}