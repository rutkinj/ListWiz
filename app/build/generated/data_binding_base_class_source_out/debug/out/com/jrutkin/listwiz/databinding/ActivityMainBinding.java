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
  public final ImageView MainIV;

  @NonNull
  public final TextView MainTVTitle;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull Button MainButtonAddTask,
      @NonNull Button MainButtonAllTasks, @NonNull ImageView MainIV,
      @NonNull TextView MainTVTitle) {
    this.rootView = rootView;
    this.MainButtonAddTask = MainButtonAddTask;
    this.MainButtonAllTasks = MainButtonAllTasks;
    this.MainIV = MainIV;
    this.MainTVTitle = MainTVTitle;
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

      id = R.id.MainIV;
      ImageView MainIV = ViewBindings.findChildViewById(rootView, id);
      if (MainIV == null) {
        break missingId;
      }

      id = R.id.MainTVTitle;
      TextView MainTVTitle = ViewBindings.findChildViewById(rootView, id);
      if (MainTVTitle == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, MainButtonAddTask,
          MainButtonAllTasks, MainIV, MainTVTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
