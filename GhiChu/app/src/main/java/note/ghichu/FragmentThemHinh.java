package note.ghichu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ThuMo on 4/29/16.
 */
public class FragmentThemHinh extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = (View) inflater.inflate(R.layout.fragment_themhinh, container);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
