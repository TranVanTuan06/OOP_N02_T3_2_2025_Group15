public class NNCollection {
    private Namenumber[] nnArray = new Namenumber[100];
    private int free;
    public NNCollection() {free = 0;}
    public void insert(Namenumber n) {
    int index = 0;
    for (int i = free++;
    i != 0 && nnArray[i-1].getLastName().compareTo(n.getLastName()) > 0;  i--) 
    {
    nnArray[i] = nnArray[i-1];
    index = i ;
    }
    nnArray[index] = n;
    }
}
