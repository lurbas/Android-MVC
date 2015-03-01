package com.lucasurbas.mvc.toolbox;


import static com.lucasurbas.mvc.toolbox.IdsComparator.Result;

/**
 * Created by Lucas on 3/1/15.
 */
public class CompareResult {

    private int position;
    private Result result;

    public CompareResult(){
        this.result = Result.MANY_ITEMS_CHANGED;
    }

    public Result getResult() {
        return result;
    }

    public int getChangedPosition() {
        return position;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setChangedPosition(int position) {
        this.position = position;
    }
}
