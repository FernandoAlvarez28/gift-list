function initCopyToClipboard(triggerQuerySelector) {
    var triggerElements = document.querySelectorAll(triggerQuerySelector);

    triggerElements.forEach(function(triggerElement) {
        triggerElement.addEventListener('click', function() {
            const targetId = this.getAttribute('data-clipboard-target');
            const target = document.getElementById(targetId);

            if (target) {
                const content = target.value;

                navigator.clipboard.writeText(content);
            }
        });
    });
}
