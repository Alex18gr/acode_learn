function showdownHighlight () {
    return [
        {
            type: "output"
          , filter (text, converter, options) {
                let left  = "<pre><code\\b[^>]*>"
                  , right = "</code></pre>"
                  , flags = "g"
                , classAttr = 'class="'
                  , replacement = (wholeMatch, match, left, right) => {
                        match = unescape(match);
                        let lang = (left.match(/class=\"([^ \"]+)/) || [])[1];

                        if (left.includes(classAttr)) {
                          let attrIndex = left.indexOf(classAttr) + classAttr.length;
                          left = left.slice(0, attrIndex) + 'hljs ' + left.slice(attrIndex);
                        } else {
                          left = left.slice(0, -1) + ' class="hljs">';
                        }
                        
                        if (lang && hljs.getLanguage(lang)) {
                            return left + hljs.highlight(lang, match).value + right;
                        } else {
                            return left + hljs.highlightAuto(match).value + right;
                        }
                    }
                  ;

                return showdown.helper.replaceRecursiveRegExp(text, replacement, left, right, flags);
            }
        }
    ];
};

function run() {
  var text = document.getElementById('sourceTA').value,
      target = document.getElementById('targetDiv'),
      converter = new showdown.Converter({
    // That's it
    extensions: [showdownHighlight]
}),
      html = converter.makeHtml(text);
    
    target.innerHTML = html;

}

