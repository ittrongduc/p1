package com.example.peter.project1.Fragment;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peter.project1.Adapter.adapter_rc_fragment_hoan_tat;
import com.example.peter.project1.Model.SanPham;
import com.example.peter.project1.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.peter.project1.Fragment.ThongTinFragment.MY_PREFS_NAME;
import static com.example.peter.project1.MuaNgayActivity.setCurrentPage;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class HoanTatFragment extends android.support.v4.app.Fragment {
    Button btn_hoantat;
    TextView tv_tongtien;
    View v;
    ArrayList<SanPham> arrayListGiohang;
    RecyclerView rc_giohang_hoantat;
    @SuppressLint("ValidFragment")
    public HoanTatFragment(ArrayList<SanPham> arrayListGiohang) {
        // Required empty public constructor
        this.arrayListGiohang = arrayListGiohang;
    }

    public HoanTatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_hoan_tat, container, false);
        Anhxa();
        setUpRcGiohang();
        ClickEvent();
        //get DAta Userinfo;
        getUserInfo();
        return v;
    }
    public void Anhxa(){
        // set tổng tiền
        tv_tongtien=v.findViewById(R.id.tv_tongtien);
        tv_tongtien.setText(CountTotalMoney()+"");

        rc_giohang_hoantat=v.findViewById(R.id.rc_giohang_fragment_hoan_tat);
        btn_hoantat=v.findViewById(R.id.btn_hoantat);
    }
    public void ClickEvent(){
        //btn_tiep_tuc_thong_tin CLick
        btn_hoantat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Đã hoàn tất", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
    }
    public void setUpRcGiohang(){
        adapter_rc_fragment_hoan_tat adapter = new adapter_rc_fragment_hoan_tat(arrayListGiohang,getContext());
        rc_giohang_hoantat.setLayoutManager(new LinearLayoutManager(getContext()));
        rc_giohang_hoantat.setAdapter(adapter);
    }
    public  int CountTotalMoney() {
        int size=0;
        for (int i = 0; i < arrayListGiohang.size(); i++) {
            size=size+arrayListGiohang.get(i).getSoluong();
        }
        return size;
    }
    public void getUserInfo(){
        SharedPreferences prefs = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
            int idName = prefs.getInt("idName", 0); //0 is the default value.
            Log.d("demo",name+"-"+idName);
        }
    }
    public void displayReceivedData(String message)
    {
        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }

}
