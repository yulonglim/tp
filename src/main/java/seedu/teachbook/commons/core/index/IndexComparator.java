package seedu.teachbook.commons.core.index;

import java.util.Comparator;


public class IndexComparator implements Comparator<Index> {

    @Override
    public int compare(Index s1, Index s2) {
        return Integer.compare(s1.getOneBased(), s2.getOneBased());
    }

}
