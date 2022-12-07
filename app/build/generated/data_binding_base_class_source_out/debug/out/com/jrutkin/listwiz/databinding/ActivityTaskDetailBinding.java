// Generated by view binder compiler. Do not edit!
package com.jrutkin.listwiz.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class ActivityTaskDetailBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView DetailTVTaskDesc;

  @NonNull
  public final TextView DetailTVTaskName;

  @NonNull
  public final TextView DetailTVTaskStatus;

  private ActivityTaskDetailBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView DetailTVTaskDesc, @NonNull TextView DetailTVTaskName,
      @NonNull TextView DetailTVTaskStatus) {
    this.rootView = rootView;
    this.DetailTVTaskDesc = DetailTVTaskDesc;
    this.DetailTVTaskName = DetailTVTaskName;
    this.DetailTVTaskStatus = DetailTVTaskStatus;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityTaskDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityTaskDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_task_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityTaskDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.DetailTVTaskDesc;
      TextView DetailTVTaskDesc = ViewBindings.findChildViewById(rootView, id);
      if (DetailTVTaskDesc == null) {
        break missingId;
      }

      id = R.id.DetailTVTaskName;
      TextView DetailTVTaskName = ViewBindings.findChildViewById(rootView, id);
      if (DetailTVTaskName == null) {
        break missingId;
      }

      id = R.id.DetailTVTaskStatus;
      TextView DetailTVTaskStatus = ViewBindings.findChildViewById(rootView, id);
      if (DetailTVTaskStatus == null) {
        break missingId;
      }

      return new ActivityTaskDetailBinding((ConstraintLayout) rootView, DetailTVTaskDesc,
          DetailTVTaskName, DetailTVTaskStatus);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
