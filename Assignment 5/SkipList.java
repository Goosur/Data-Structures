public class SkipList<T extends Comparable> implements SearchList<T> {
    SNode start;
    SNode end;
    SNode nodeSentinalTop;
    int len;

    public SkipList() {
        this.start = null;
        this.end = null;
        this.nodeSentinalTop = new SNode<T>(null);
        this.nodeSentinalTop.setNext(this.start);
        this.len = 0;
    }

    private SNode secretSearch(T data) {
        SNode nodeCurrent = nodeSentinalTop;
        while (true) {
            while (nodeCurrent.getNext() != null && !(nodeCurrent.getNext().getData().compareTo(data) > 0)) {
                nodeCurrent = nodeCurrent.getNext();
            }
            
            if (nodeCurrent.getDown() != null) {
                nodeCurrent = nodeCurrent.getDown();
            }

            if (nodeCurrent.getDown() == null && (nodeCurrent.getNext() == null || nodeCurrent.getNext().getData().compareTo(data) > 0)){
                return nodeCurrent;
            }
        }
    }

    @Override
    public void insert(T data) {
        SNode nodeNewCurrent = new SNode<T>(data);
        SNode nodeTarget = this.secretSearch(data);
        SNode nodeNewNext;

        nodeNewCurrent.setNext(nodeTarget.getNext());
        nodeNewCurrent.setPrevious(nodeTarget);
        if (nodeTarget.getNext() != null) {
            nodeTarget.getNext().setPrevious(nodeNewCurrent);
        }
        nodeTarget.setNext(nodeNewCurrent);

        double randomValue = Math.random();

        while (randomValue > 0.5) {
            nodeNewNext = new SNode<T>(data);
            nodeNewCurrent.setUp(nodeNewNext);
            nodeNewNext.setDown(nodeNewCurrent);
            nodeNewCurrent = nodeNewNext;

            while (nodeTarget.getUp() == null) {
                if (nodeTarget.getPrevious() == null) {
                    SNode nodeSentinalNew = new SNode<T>(null);
                    this.nodeSentinalTop.setUp(nodeSentinalNew);
                    nodeSentinalNew.setDown(this.nodeSentinalTop);
                } else {
                    nodeTarget = nodeTarget.getPrevious();
                }

                //System.out.println("STUCK HERE");
            }

            nodeTarget = nodeTarget.getUp();

            nodeNewCurrent.setNext(nodeTarget.getNext());
            nodeNewCurrent.setPrevious(nodeTarget);
            if (nodeTarget.getNext() != null) {
                nodeTarget.getNext().setPrevious(nodeNewCurrent);
            }
            nodeTarget.setNext(nodeNewCurrent);

            randomValue = Math.random();
        }
    }

    @Override
    public boolean search(T data) {
        SNode targetNode = this.secretSearch(data);
        if (targetNode.getData().compareTo(data) == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(T data) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void print() {
        SNode current = start;
        while (current != null) {
            System.out.print(current.getData() + " ");
        }
        System.out.println();
    }
    
}
