"use strict";
exports.__esModule = true;
var JOB_BASE = /** @class */ (function () {
    function JOB_BASE(JID, BCODE, BPCODE, DATASTATE, DATASOURCE, JDISTRICT, JTITLE, REGTYPE, PROPOSER, JLOCATION) {
        this.JID = JID || "";
        this.BCODE = BCODE || "";
        this.BPCODE = BPCODE || "";
        this.DATASTATE = DATASTATE || "";
        this.DATASOURCE = DATASOURCE || "";
        this.JDISTRICT = JDISTRICT;
        this.JTITLE = JTITLE || "";
        this.REGTYPE = REGTYPE || "";
        this.PROPOSER = PROPOSER || "";
        this.JLOCATION = JLOCATION || "";
    }
    return JOB_BASE;
}());
exports.JOB_BASE = JOB_BASE;
