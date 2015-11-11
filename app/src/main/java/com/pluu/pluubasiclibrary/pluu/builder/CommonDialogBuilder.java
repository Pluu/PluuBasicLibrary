package com.pluu.pluubasiclibrary.pluu.builder;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pluu.pluubasiclibrary.R;


/**
 * Common Dialog Builder
 * Created by nohhs on 2015-10-29.
 */
public class CommonDialogBuilder {

	private static class CommonDialog extends Dialog {

		private final CommonDialogBuilder builder;

		public CommonDialog(Context context, CommonDialogBuilder builder) {
			super(context, android.R.style.Theme_Translucent_NoTitleBar);
			this.builder = builder;
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
			lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
			lpWindow.dimAmount = 0.8f;
			getWindow().setAttributes(lpWindow);

			setContentView(R.layout.dialog_common);
			initView();
		}

		private void initView() {
			final String title = builder.title;
			final String message = builder.message;
			final int titleBgResId = builder.titleBgResId;
			final int titleTextColorResId = builder.titleTextColorResId;
			final View contentView = builder.contentView;
			final boolean showTitleDivider = builder.showTitleDivider;
			final boolean showButtonDivider = builder.showButtonDivider;
			final int positiveBgResId = builder.positiveBgResId;
			final int negativeBgResId = builder.negativeBgResId;
			final String positiveBtnText = builder.positiveBtnText;
			final String negativeBtnText = builder.negativeBtnText;
			final int positiveTextColorResId = builder.positiveTextColorResId;
			final int negativeTextColorResId = builder.negativeTextColorResId;
			final OnClickListener positiveListener = builder.positiveListener;
			final OnClickListener negativeListener = builder.negativeListener;

			TextView titleView = (TextView) findViewById(R.id.title);
			if (title != null && title.length() > 0) {
				titleView.setText(title);
			} else {
				findViewById(R.id.popup_header).setVisibility(View.GONE);
			}

			if (titleBgResId > 0) {
				titleView.setBackgroundResource(titleBgResId);
			}

			if (titleTextColorResId > 0) {
				titleView.setTextColor(ContextCompat.getColor(getContext(), titleTextColorResId));
			}

			View titleDivider = findViewById(R.id.divide);
			if (showTitleDivider) {
				titleDivider.setVisibility(View.VISIBLE);
			} else {
				titleDivider.setVisibility(View.GONE);
			}

			View buttonDivider = findViewById(R.id.divide2);
			if (showButtonDivider) {
				buttonDivider.setVisibility(View.VISIBLE);
			} else {
				buttonDivider.setVisibility(View.GONE);
			}

			TextView messageView = (TextView) findViewById(R.id.content);
			if (message != null && message.length() > 0) {
				messageView.setText(message);
				messageView.setMovementMethod(new ScrollingMovementMethod());
			} else {
				messageView.setVisibility(View.GONE);
			}

			if (contentView != null) {
				FrameLayout container = (FrameLayout)findViewById(R.id.content_group);
				container.setVisibility(View.VISIBLE);
				container.addView(contentView);
			}

			Button positiveBtn = (Button) findViewById(R.id.bt_right);
			if (positiveBtnText != null && positiveBtnText.length() > 0) {
				positiveBtn.setText(positiveBtnText);
				if (positiveTextColorResId > 0) {
					positiveBtn.setTextColor(
						ContextCompat.getColor(getContext(), positiveTextColorResId));
				}
				if (positiveBgResId > 0) {
					positiveBtn.setBackgroundResource(positiveBgResId);
				}
			} else {
				positiveBtn.setVisibility(View.GONE);
			}

			Button negativeBtn = (Button) findViewById(R.id.bt_left);
			if (negativeBtnText != null && negativeBtnText.length() > 0) {
				negativeBtn.setText(negativeBtnText);
				if (negativeTextColorResId > 0) {
					negativeBtn.setTextColor(
						ContextCompat.getColor(getContext(), negativeTextColorResId));
				}
				if (negativeBgResId > 0) {
					negativeBtn.setBackgroundResource(negativeBgResId);
				}
			} else {
				negativeBtn.setVisibility(View.GONE);
			}

			if (positiveBtn.getVisibility() == View.VISIBLE && negativeBtn.getVisibility() == View.GONE) {
				if (positiveBgResId == 0) {
					positiveBtn.setBackgroundResource(R.drawable.selector_btn_primary_c);
				} else {
					positiveBtn.setBackgroundResource(positiveBgResId);
				}
			}

			negativeBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (negativeListener != null) {
						negativeListener.onClick(CommonDialog.this, 0);
					}
					dismiss();
				}
			});

			positiveBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (positiveListener != null) {
						positiveListener.onClick(CommonDialog.this, 0);
					}
					dismiss();
				}
			});

			findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dismiss();
				}
			});
			findViewById(R.id.popup).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// skip
				}
			});
		}
	}

	private Context context = null;
	private String title = null;
	private String message = null;
	private View contentView = null;
	private int titleBgResId = 0;
	private int titleTextColorResId = 0;
	private boolean showTitleDivider = true;
	private boolean showButtonDivider = true;
	private String positiveBtnText = null;
	private String negativeBtnText = null;
	private int positiveTextColorResId = 0;
	private int negativeTextColorResId = 0;
	private int positiveBgResId = 0;
	private int negativeBgResId = 0;
	private DialogInterface.OnClickListener positiveListener = null;
	private DialogInterface.OnClickListener negativeListener = null;

	public CommonDialogBuilder(Context context) {
		this.context = context;
	}

	public CommonDialogBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public CommonDialogBuilder setTitle(int titleResId) {
		this.title = context.getString(titleResId);
		return this;
	}

	public CommonDialogBuilder setMessage(String message) {
		this.message = message;
		return this;
	}

	public CommonDialogBuilder setMessage(int messageResId) {
		this.message = context.getString(messageResId);
		return this;
	}

	public CommonDialogBuilder setPositiveButton(int positiveResId, DialogInterface.OnClickListener positiveListener) {
		this.positiveBtnText = context.getString(positiveResId);
		this.positiveListener = positiveListener;
		return this;
	}

	public CommonDialogBuilder setNegativeButton(int negativeResId, DialogInterface.OnClickListener negativeListener) {
		this.negativeBtnText = context.getString(negativeResId);
		this.negativeListener = negativeListener;
		return this;
	}

	public CommonDialogBuilder setView(View view) {
		this.contentView = view;
		return this;
	}

	public CommonDialogBuilder setTitleBgResId(int titleBgResId) {
		this.titleBgResId = titleBgResId;
		return this;
	}

	public CommonDialogBuilder setShowTitleDivider(boolean showTitleDivider) {
		this.showTitleDivider = showTitleDivider;
		return this;
	}

	public CommonDialogBuilder setShowButtonDivider(boolean showButtonDivider) {
		this.showButtonDivider = showButtonDivider;
		return this;
	}

	public CommonDialogBuilder setTitleTextColorResId(int titleTextColorResId) {
		this.titleTextColorResId = titleTextColorResId;
		return this;
	}

	public CommonDialogBuilder setPositiveTextColorResId(int positiveTextColorResId) {
		this.positiveTextColorResId = positiveTextColorResId;
		return this;
	}

	public CommonDialogBuilder setNegativeTextColorResId(int negativeTextColorResId) {
		this.negativeTextColorResId = negativeTextColorResId;
		return this;
	}

	public CommonDialogBuilder setPositiveBgResId(int positiveBgResId) {
		this.positiveBgResId = positiveBgResId;
		return this;
	}

	public CommonDialogBuilder setNegativeBgResId(int negativeBgResId) {
		this.negativeBgResId = negativeBgResId;
		return this;
	}

	public Dialog create() {
		return new CommonDialog(context, this);
	}

}
