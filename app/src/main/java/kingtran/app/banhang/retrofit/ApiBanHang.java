package kingtran.app.banhang.retrofit;

import android.database.Observable;

import kingtran.app.banhang.model.LoaiSpModel;
import retrofit2.http.GET;

public interface ApiBanHang {
    @GET("getloaisp.php")
    Observable<LoaiSpModel> getLoaiSp();

}
