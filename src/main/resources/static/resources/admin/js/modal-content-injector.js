$(document).ready(function() {
    $('[data-target-content-uri]').each(function(index, element) {
        var $this = $(this);
        $this.click(function() {
            $.get($this.attr('data-target-content-uri'), function(data, status) {
                if (status === 'success') {
                    const modalId = $this.attr('data-target');
                    $(`${modalId} .modal-content`).html(data)
                    initCopyToClipboard(`${modalId} .copy`)
                    initFileSizeLimit(`${modalId} .js-file-input-size-limit-div`)
                }
            });
        });
    });
});
