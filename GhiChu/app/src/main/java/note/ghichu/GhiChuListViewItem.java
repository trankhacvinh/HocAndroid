package note.ghichu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import note.ghichu.Model.GhiChu;

/**
 * Created by tranv on 4/9/2016.
 */
public class GhiChuListViewItem extends BaseAdapter {
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

        TextView twTieuDe = (TextView) convertView.findViewById(R.id.twTieuDe);
        TextView twNoiDung = (TextView) convertView.findViewById(R.id.twNoiDung);
        ImageView ivKhungAnh = (ImageView) convertView.findViewById(R.id.ivKhungAnh);
        GhiChu ghiChu = getItem(position);
        twTieuDe.setText(ghiChu.getTieuDe());
        twNoiDung.setText(ghiChu.getNoiDung());
        Bitmap b = BitmapFactory.decodeByteArray(ghiChu.getImageByte(), 0, ghiChu.getImageByte().length);
        ivKhungAnh.setImageBitmap(b);
        return convertView;
    }
}
