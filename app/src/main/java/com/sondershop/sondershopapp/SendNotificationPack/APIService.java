package com.sondershop.sondershopapp.SendNotificationPack;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAASx2yI_4:APA91bFNNFfDG2m-1Atwvun5zMk3TrFOavPSlZFoAkdPZgBMM1vssx-6fZQoXpi33oxU8l1vy24hffdF-xLoCs5j-b-AhcDck8hPBUfZFHEdbneHKmXvX3kKgeeNT2ZAIgMU4cn_-B8w"
            }
    )


    @POST("fcm/send")
    Call<MyResponse> sendNotifcation(@Body NotificationSender body);
 }

