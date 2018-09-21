/**
 * Created by lovo_bdk on 15-12-17.
 */
!(function(win, doc){
    function setFontSize() {
        // 获取window 宽度
        // zepto实现 $(window).width()就是这么干的
        var winWidth =  window.innerWidth;
        doc.documentElement.style.fontSize = (winWidth / 375) * 10 + 'px' ;

        //设置页面元素根元素的px大小，然后所有rem以此为基准。
        //640为开发时候的页面宽度，100为基准px大小， 可以设置任意数字，方便开发时候rem计算

    }

    var evt = 'onorientationchange' in win ? 'orientationchange' : 'resize';

    var timer = null;

    win.addEventListener(evt, function () {
        clearTimeout(timer);

        timer = setTimeout(setFontSize, 300);
    }, false);

    win.addEventListener("pageshow", function(e) {
        if (e.persisted) {
            clearTimeout(timer);

            timer = setTimeout(setFontSize, 300);
        }
    }, false);

    //初始化
    setFontSize();

}(window, document));