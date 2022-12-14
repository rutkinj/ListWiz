// Generated by view binder compiler. Do not edit!
package com.jrutkin.listwiz.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.jrutkin.listwiz.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button MainButtonAddTask;

  @NonNull
  public final Button MainButtonAllTasks;

  @NonNull
  public final Button MainButtonSignIn;

  @NonNull
  public final Button MainButtonSignOut;

  @NonNull
  public final Button MainButtonSignUp;

  @NonNull
  public final ImageView MainIVToUserProfile;

  @NonNull
  public final RecyclerView MainRV;

  @NonNull
  public final TextView MainTVUsername;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull Button MainButtonAddTask,
      @NonNull Button MainButtonAllTasks, @NonNull Button MainButtonSignIn,
      @NonNull Button MainButtonSignOut, @NonNull Button MainButtonSignUp,
      @NonNull ImageView MainIVToUserProfile, @NonNull RecyclerView MainRV,
      @NonNull TextView MainTVUsername) {
    this.rootView = rootView;
    this.MainButtonAddTask = MainButtonAddTask;
    this.MainButtonAllTasks = MainButtonAllTasks;
    this.MainButtonSignIn = MainButtonSignIn;
    this.MainButtonSignOut = MainButtonSignOut;
    this.MainButtonSignUp = MainButtonSignUp;
    this.MainIVToUserProfile = MainIVToUserProfile;
    this.MainRV = MainRV;
    this.MainTVUsername = MainTVUsername;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.MainButtonAddTask;
      Button MainButtonAddTask = ViewBindings.findChildViewById(rootView, id);
      if (MainButtonAddTask == null) {
        break missingId;
      }

      id = R.id.MainButtonAllTasks;
      Button MainButtonAllTasks = ViewBindings.findChildViewById(rootView, id);
      if (MainButtonAllTasks == null) {
        break missingId;
      }

      id = R.id.MainButtonSignIn;
      Button MainButtonSignIn = ViewBindings.findChildViewById(rootView, id);
      if (MainButtonSignIn == null) {
        break missingId;
      }

      id = R.id.MainButtonSignOut;
      Button MainButtonSignOut = ViewBindings.findChildViewById(rootView, id);
      if (MainButtonSignOut == null) {
        break missingId;
      }

      id = R.id.MainButtonSignUp;
      Button MainButtonSignUp = ViewBindings.findChildViewById(rootView, id);
      if (MainButtonSignUp == null) {
        break missingId;
      }

      id = R.id.MainIVToUserProfile;
      ImageView MainIVToUserProfile = ViewBindings.findChildViewById(rootView, id);
      if (MainIVToUserProfile == null) {
        break missingId;
      }

      id = R.id.MainRV;
      RecyclerView MainRV = ViewBindings.findChildViewById(rootView, id);
      if (MainRV == null) {
        break missingId;
      }

      id = R.id.MainTVUsername;
      TextView MainTVUsername = ViewBindings.findChildViewById(rootView, id);
      if (MainTVUsername == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, MainButtonAddTask,
          MainButtonAllTasks, MainButtonSignIn, MainButtonSignOut, MainButtonSignUp,
          MainIVToUserProfile, MainRV, MainTVUsername);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
