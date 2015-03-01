package com.lucasurbas.mvc.toolbox;

import java.util.List;

/**
 * Created by Lucas on 3/1/15.
 */
public class IdsComparator {

    public enum Result {
        ONE_ITEM_ADDED, ONE_ITEM_REMOVED, MANY_ITEMS_CHANGED, SAME_ITEMS;
    }

    public static CompareResult compare(List<String> oldIds, List<String> ids) {
        CompareResult compareResult = new CompareResult();

        int diff = oldIds.size() - ids.size();

        if (diff == 0 && isTheSame(oldIds, ids)) {
            compareResult.setResult(Result.SAME_ITEMS);
            return compareResult;
        } else if (diff == -1) {
            int position = getAddedItemPosition(oldIds, ids);
            if (position != -1) {
                compareResult.setResult(Result.ONE_ITEM_ADDED);
                compareResult.setChangedPosition(position);
                return compareResult;
            }
        } else if (diff == 1) {
            int position = getRemovedItemPosition(oldIds, ids);
            if (position != -1) {
                compareResult.setResult(Result.ONE_ITEM_REMOVED);
                compareResult.setChangedPosition(position);
                return compareResult;
            }
        }
        compareResult.setResult(Result.MANY_ITEMS_CHANGED);
        return compareResult;
    }

    private static boolean isTheSame(List<String> oldIds, List<String> ids) {
        if (oldIds.size() != ids.size()) {
            return false;
        }
        for (int i = 0; i < ids.size(); i++) {
            if (!oldIds.get(i).equals(ids.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static int getAddedItemPosition(List<String> oldIds, List<String> ids) {
        if ((oldIds.size() + 1) != ids.size()) {
            return -1;
        }
        int position = -1;
        for (int i = 0; i < ids.size(); i++) {
            if (position == -1 && oldIds.size() == i) {
                position = i;
                return position;
            } else if (position == -1 && !oldIds.get(i).equals(ids.get(i))) {
                position = i;
            } else if (position != -1) {
                if (!oldIds.get(i - 1).equals(ids.get(i))) {
                    return -1;
                }
            }
        }
        return position;
    }

    private static int getRemovedItemPosition(List<String> oldIds, List<String> ids) {
        return getAddedItemPosition(ids, oldIds);
    }
}
