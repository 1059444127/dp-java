 /*!
 * cxSlide 1.0
 */
(function(e) {
    e.fn.cxSlide = function(t) {
        if (!this.length)
            return;
        t = e.extend({}, e.cxSlide.defaults, t);
        var n = this, r = {};
        r.fn = {};
        var i;
        r.box = n.find(".box"), r.list = r.box.find(".list"), r.items = r.list.find("li"), r.itemSum = r.items.length;
        if (r.itemSum <= 1)
            return;
        r.numList = n.find(".btn"), r.numBtns = r.numList.find("li"), r.plusBtn = n.find(".plus"), r.minusBtn = n.find(".minus"), r.boxWidth = r.box.width(), r.boxHeight = r.box.height(), r.s = 0;
        if (t.btn && !r.numList.length) {
            i = "";
            for (var s = 1; s <= r.itemSum; s++)
                i += "<li class='b_" + s + "'>" + s + "</li>";
            r.numList = e("<ul></ul>", {"class": "btn",html: i}).appendTo(n), r.numBtns = r.numList.find("li")
        }
        t.plus && !r.plusBtn.length && (r.plusBtn = e("<div></div>", {"class": "plus"}).appendTo(n)), t.minus && !r.minusBtn.length && (r.minusBtn = e("<div></div>", {"class": "minus"}).appendTo(n)), r.fn.on = function() {
            if (!t.auto)
                return;
            r.fn.off(), r.run = setTimeout(function() {
                r.fn.goto()
            }, t.time)
        }, r.fn.off = function() {
            typeof r.run != "undefined" && clearTimeout(r.run)
        }, r.fn.checkBtn = function(e) {
            r.numList.length && r.numBtns.eq(e).addClass("selected").siblings("li").removeClass("selected")
        }, r.fn.goto = function(e) {
            r.fn.off();
            var n = typeof e == "undefined" ? r.s + 1 : parseInt(e, 10), i = r.s, s = r.itemSum - 1;
            if (n == r.s) {
                r.fn.on();
                return
            }
            n > s ? n = 0 : n < 0 && (n = s), r.fn.checkBtn(n);
            var o;
            switch (t.type) {
                case "x":
                    o = r.boxWidth * n, n == 0 && i == s ? (r.items.eq(0).css({left: r.boxWidth * r.itemSum}), o = r.boxWidth * r.itemSum) : i == 0 && (r.items.eq(0).css({left: ""}), r.box.scrollLeft(0)), r.box.stop(!0, !1).animate({scrollLeft: o}, t.speed);
                    break;
                case "y":
                    o = r.boxHeight * n, n == 0 && i == s ? (r.items.eq(0).css({top: r.boxHeight * r.itemSum}), o = r.boxHeight * r.itemSum) : i == 0 && (r.items.eq(0).css({top: ""}), r.box.scrollTop(0)), r.box.stop(!0, !1).animate({scrollTop: o}, t.speed);
                    break;
                case "fade":
                    r.items.css({display: "none",position: "absolute",top: 0,left: 0,zIndex: ""}), r.items.eq(i).css({display: "",zIndex: 1}), r.items.eq(n).css({zIndex: 2}).fadeIn(t.speed);
                    break;
                case "toggle":
                    r.items.eq(n).show().siblings("li").hide()
            }
            r.s = n, r.box.queue(function() {
                r.fn.on(), r.box.dequeue()
            })
        }, r.box.hover(function() {
            r.fn.off()
        }, function() {
            r.fn.on()
        }), t.btn && r.numList.delegate("li", t.events, function() {
            r.fn.goto(r.numBtns.index(e(this)))
        }), t.plus && r.plusBtn.bind(t.events, function() {
            r.fn.goto()
        }), t.minus && r.minusBtn.bind(t.events, function() {
            r.fn.goto(r.s - 1)
        }), r.fn.checkBtn(t.start), r.fn.goto(t.start)
    }, e.cxSlide = {defaults: {events: "click",type: "x",start: 0,speed: 800,time: 5e3,auto: !0,btn: !0,plus: !1,minus: !1}}
})(jQuery);
