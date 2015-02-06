package com.pluu.pluubasiclibrary.pluu.builder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.ImageView;

/**
 * Mask Image Builder
 * Created by PLUUSYSTEM on 2015-02-06.
 */
public class ImageMaskBuilder {
	private Context mContext;

	private Bitmap[] mView = new Bitmap[3];

	private final int INDEX_PHOTO = 0;
	private final int INDEX_MASK = 1;
	private final int INDEX_BG = 2;

	private String mMsg;
	private int mMsgColor, mMsgBgColor;
	private float mMsgBgHeight;
	private int mMsgSpSize;

	public ImageMaskBuilder(Context context)
	{
		super();
		this.mContext = context;

		init();
	}

	private void init()
	{
		mMsgColor = Color.WHITE;
		mMsgBgColor = Color.parseColor("#66000000");
		mMsgBgHeight = 0.5f;
		mMsgSpSize = 15;
	}

	public ImageMaskBuilder setPhotoImage(int photoId)
	{
		setImageView(INDEX_PHOTO, photoId);
		return this;
	}

	public ImageMaskBuilder setPhotoImage(Bitmap photo)
	{
		setImageView(INDEX_PHOTO, photo);
		return this;
	}

	public ImageMaskBuilder setBackgroundImage(int bgId)
	{
		setImageView(INDEX_BG, bgId);
		return this;
	}

	public ImageMaskBuilder setBackgroundImage(Bitmap bg)
	{
		setImageView(INDEX_BG, bg);
		return this;
	}

	public ImageMaskBuilder setMaskImage(int maskId)
	{
		setImageView(INDEX_MASK, maskId);
		return this;
	}

	public ImageMaskBuilder setMaskImage(Bitmap mask)
	{
		setImageView(INDEX_MASK, mask);
		return this;
	}

	/**
	 * 이미지 설정
	 * @param index Image INDEX
	 * @param photoId Resource Id
	 */
	private void setImageView(int index, int photoId)
	{
		Resources res = mContext.getResources();
		setImageView(index, BitmapFactory.decodeResource(res, photoId));
	}

	/**
	 * 이미지 설정
	 * @param index Image INDEX
	 * @param photo Photo Bitmap
	 */
	private void setImageView(int index, Bitmap photo)
	{
		mView[index] = photo;
	}

	public ImageMaskBuilder setMessage(int msgId)
	{
		return setMessage(mContext.getString(msgId));
	}

	public ImageMaskBuilder setMessage(String msg)
	{
		this.mMsg = msg;
		return this;
	}

	/**
	 * Text Size
	 * @param messageSize
	 */
	public void setMessageSize(int messageSize)
	{
		this.mMsgSpSize = messageSize;
	}

	/**
	 * Message Height
	 * @param height 0 ~ 1f
	 * @return ImageMaskBuilder
	 */
	public ImageMaskBuilder setMessageHeight(float height)
	{
		height = Math.min(1.0f, Math.max(0, height));
		return this;
	}

	@SuppressWarnings("deprecation")
	public void create(ImageView target)
	{
		Resources res = mContext.getResources();

		// Create Photo by Mask Image Size
		mView[INDEX_PHOTO] = Bitmap.createScaledBitmap(mView[INDEX_PHOTO],
			mView[INDEX_MASK].getWidth(),
			mView[INDEX_MASK].getHeight(), true);

		Bitmap result = Bitmap.createBitmap(mView[INDEX_MASK].getWidth(),
			mView[INDEX_MASK].getHeight(),
			Bitmap.Config.ARGB_8888);
		Canvas mCanvas = new Canvas(result);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

		mCanvas.drawBitmap(mView[INDEX_PHOTO], 0, 0, null);

		if (!TextUtils.isEmpty(mMsg))
		{
			// Text Background Layout
			Rect mTextLayout = new Rect(0,
				(int) (mView[INDEX_MASK].getHeight() * mMsgBgHeight),
				mView[INDEX_MASK].getWidth(),
				mView[INDEX_MASK].getHeight());

			// Text Background Paint
			Paint mTextBgPaint = new Paint();
			mTextBgPaint.setColor(mMsgBgColor);
			mTextBgPaint.setStyle(Paint.Style.FILL);
			mCanvas.drawRect(mTextLayout, mTextBgPaint);

			// Text Size
			float textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mMsgSpSize, res.getDisplayMetrics());

			// Text Paint
			Paint mTextPaint = new Paint();
			mTextPaint.setColor(mMsgColor);
			mTextPaint.setTextAlign(Paint.Align.CENTER);
			mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
			mTextPaint.setTextSize(textSize);

			// Draw Text
			mCanvas.drawText(mMsg,
				mTextLayout.left + (mTextLayout.width() / 2),
				mTextLayout.top + (mTextLayout.height() / 2),
				mTextPaint);
		}

		mCanvas.drawBitmap(mView[INDEX_MASK], 0, 0, paint);
		paint.setXfermode(null);

		target.setImageBitmap(result);
		target.setScaleType(ImageView.ScaleType.FIT_XY);

		if (mView[INDEX_BG] != null)
		{
			target.setBackgroundDrawable(new BitmapDrawable(res, mView[INDEX_BG]));
		}
	}
}
