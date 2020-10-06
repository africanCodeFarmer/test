"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var component_1 = require("../common/component");
var color_1 = require("../common/color");
component_1.VantComponent({
    classes: ['desc-class'],
    props: {
        icon: String,
        steps: Array,
        active: Number,
        direction: {
            type: String,
            value: 'horizontal'
        },
        activeColor: {
            type: String,
            value: color_1.GREEN
        },
        inactiveColor: {
            type: String,
            value: color_1.GRAY_DARK
        },
        activeIcon: {
            type: String,
            value: 'checked'
        },
        inactiveIcon: String,
    },
    methods: {
        // getLoadingColor: function (checked) {
        //     var _a = this.data, activeColor = _a.activeColor, inactiveColor = _a.inactiveColor;
        //     return checked ? activeColor || color_1.BLUE : inactiveColor || color_1.GRAY_DARK;
        // },
        onClick: function (e) {
            var text = e.target.dataset.text

            var node = {
                name: text,
            }
            this.$emit('click-step', node);

            // var _a = this.data, activeValue = _a.activeValue, inactiveValue = _a.inactiveValue;
            // if (!this.data.disabled && !this.data.loading) {
            //     var checked = this.data.checked === activeValue;
            //     var value = checked ? inactiveValue : activeValue;
            //     this.$emit('input', value);
            //     this.$emit('change', value);
            // }
        }
    },
});
