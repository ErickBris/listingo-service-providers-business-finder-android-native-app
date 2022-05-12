package com.tregix.serviceprovider.Retrofit;

import com.tregix.serviceprovider.Model.ApiResponse;
import com.tregix.serviceprovider.Model.Appointment;
import com.tregix.serviceprovider.Model.AppointmentSlot;
import com.tregix.serviceprovider.Model.BookingRequest;
import com.tregix.serviceprovider.Model.BookingResponse;
import com.tregix.serviceprovider.Model.ConfirmAppointment;
import com.tregix.serviceprovider.Model.Countries;
import com.tregix.serviceprovider.Model.JobItem;
import com.tregix.serviceprovider.Model.Login.User;
import com.tregix.serviceprovider.Model.LoginData;
import com.tregix.serviceprovider.Model.ManageAppointmentRequest;
import com.tregix.serviceprovider.Model.ManagePrivacyRequest;
import com.tregix.serviceprovider.Model.ManageServicesRequestParam;
import com.tregix.serviceprovider.Model.Provider.BusinessHours;
import com.tregix.serviceprovider.Model.Provider.MarkFavoriteParam;
import com.tregix.serviceprovider.Model.Provider.PrivacySettings;
import com.tregix.serviceprovider.Model.Provider.ProfileServices;
import com.tregix.serviceprovider.Model.Provider.ProviderModel;
import com.tregix.serviceprovider.Model.Provider.ProviderReviewListData;
import com.tregix.serviceprovider.Model.RegisterBusiness;
import com.tregix.serviceprovider.Model.RequestSlots;
import com.tregix.serviceprovider.Model.ResetPassword;
import com.tregix.serviceprovider.Model.ReviewProvider;
import com.tregix.serviceprovider.Model.TokenRequestParam;
import com.tregix.serviceprovider.Model.categories.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Confiz123 on 11/21/2017.
 */

public interface ProviderApi {

        String BASE_SITE = "http://wp.getlistingo.com/";

        String BASE_URL = BASE_SITE + "wp-json/api/v1/";

        @GET("categories")
        Call<List<Category>> loadCategories();

        @GET("configs/countries")
        Call<List<Countries>> loadCountries();

        @GET("providers")
        Call<List<ProviderModel>> searchProvider(@Query("user_id") int userid,
                                                 @Query("category") String category,
                                                 @Query("keyword") String keyword,
                                                 @Query("geo") String geo,
                                                 @Query("geo_distance") String geo_distance,
                                                 @Query("country") String country,
                                                 @Query("city") String city,
                                                 @Query("zip") String zip,
                                                 @Query("lat") String lat,
                                                 @Query("long") String longitude);

        @GET("providers")
        Call<List<ProviderModel>> getFeaturedProviders(@Query("user_id") int userid, @Query("featured") String featured);

        @POST("providers")
        Call<User> registerUser(@Body RegisterBusiness post);

        @POST("user/login")
        Call<User> loginUser(@Body LoginData post);

        @POST("providers/appointment")
        Call<BookingResponse> makeAppointment(@Body BookingRequest post);

        @POST("providers/confirm-appointment")
        Call<ApiResponse> confirmAppointment(@Body ConfirmAppointment post);

        @GET("user/appointments")
        Call<List<Appointment>> getUserAppointments(@Query("user_id") long userid);

        @POST("user/reset-password")
        Call<ApiResponse> recoverPassword(@Body ResetPassword post);

        @POST("providers/appointment/slots")
        Call<List<AppointmentSlot>> getSlotsOfDate(@Body RequestSlots post);

        @POST("providers/save-rating")
        Call<ApiResponse> saveProviderRating(@Body ReviewProvider rating);

        @POST("user/token")
        Call<ApiResponse> saveUserToken(@Body TokenRequestParam rating);

        @GET("user/services")
        Call<List<ProfileServices>> getUserServices(@Query("user_id") long userId);

        @POST("user/services")
        Call<ApiResponse> updateUserServices(@Body ManageServicesRequestParam params);

        @POST("providers/reviews")
        Call<List<ProviderReviewListData>> getProviderReviews(@Body ReviewProvider params);

        @GET("user/favorites")
        Call<List<ProviderModel>> getUserFavorites(@Query("user_id") long userId);

        @POST("user/favorites")
        Call<ApiResponse> updateUserFavorites(@Body MarkFavoriteParam params);

        @GET("providers/manage-appointments")
        Call<List<Appointment>> getProviderAppointments(@Query("provider_id") long providerId);

        @POST("providers/appointments-status")
        Call<ApiResponse> updateProviderAppointments(@Body ManageAppointmentRequest params);

        @GET("providers/privacy-settings")
        Call<PrivacySettings> getPrivacySettings(@Query("publisher_id") long providerId);

        @POST("providers/privacy-settings")
        Call<ApiResponse> updatePrivacySettings(@Body ManagePrivacyRequest request);

        @GET("jobs")
        Call<List<JobItem>> getAllJobs();

        @GET("providers/business-hours")
        Call<BusinessHours> getBusinessHour(@Query("publisher_id") long providerId);

        @POST("providers/business-hours")
        Call<ApiResponse> updateBusinessHour(@Body ManagePrivacyRequest request);

}
