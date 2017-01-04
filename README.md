# Android-Barrage
安卓上的弹幕简单实现 
# Use
BarrageView barrageView = (BarrageView) findViewById(R.id.barrageUI);
BarrageItem barrageItem = new BarrageItem(800,800,"落霞与孤鹜齐飞",10);
barrageView.addBitmap(barrageItem.getBitmap());
