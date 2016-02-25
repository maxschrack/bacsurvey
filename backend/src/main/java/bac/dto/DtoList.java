package bac.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by max on 13/02/16.
 */
public class DtoList<T extends Dto> extends ArrayList<T> {
    public DtoList(){
        super();
    }

    public DtoList(List<T> list){
        super(list);
    }

    @Override
    public String toString() {
        if(!this.isEmpty()){
            return "DtoList of " + this.get(0).getClass().getSimpleName() + ": " + super.toString();
        }else{
            return "Empty DtoList";
        }
    }
}
