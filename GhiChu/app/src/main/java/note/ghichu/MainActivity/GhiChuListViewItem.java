package note.ghichu.MainActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import note.ghichu.Model.GhiChu;
import note.ghichu.R;

/**
 * Created by tranv on 4/9/2016.
 */
public class GhiChuListViewItem extends BaseAdapter {

    private int[] colors = new int[] { 0xFF000000, 0xFF333333 };

    private List<GhiChu> danhSachGhiChu = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context c;

    public GhiChuListViewItem(List<GhiChu> danhSachGhiChu, Context context) {
        this.danhSachGhiChu = danhSachGhiChu;
        this.c = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return danhSachGhiChu.size();
    }

    @Override
    public GhiChu getItem(int position) {
        return danhSachGhiChu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.ghichu_listviewitem, null);
        }

        int colorPos = position % colors.length;
        convertView.setBackgroundColor(colors[colorPos]);

        TextView twTieuDe = (TextView) convertView.findViewById(R.id.twTieuDe);
        TextView twNgayTao = (TextView) convertView.findViewById(R.id.twNgayTao);
        TextView twNoiDung = (TextView) convertView.findViewById(R.id.twNoiDung);
        //ImageView ivKhungAnh = (ImageView) convertView.findViewById(R.id.ivKhungAnh);
        GhiChu ghiChu = getItem(position);
        twTieuDe.setText(ghiChu.getTieuDe());
        twNgayTao.setText(ghiChu.getNgayTao());
        twNoiDung.setText(ghiChu.getN);

        //if(ghiChu.getImageByte() != null && ghiChu.getImageByte().length > 0){
        //    Bitmap b = BitmapFactory.decodeByteArray(ghiChu.getImageByte(), 0, ghiChu.getImageByte().length);
        //    ivKhungAnh.setImageBitmap(b);
       // }
        return convertView;
    }
}
