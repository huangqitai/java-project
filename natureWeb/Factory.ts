import {JOB_BASE} from "./JOB_BASE";
import {JOB_SQRXXB_LINK} from "./JOB_SQRXXB_LINK";


interface BackObject {
    [key: string]: any
}

export class Factory {


     buildJOB_BASE(obj):Array<JOB_BASE> {
        let data = obj;
        let  job_bases : Array<JOB_BASE> = new Array<JOB_BASE>();

            let job_base: JOB_BASE =new JOB_BASE(data.JID,data.BCODE,data.BPCODE,data.DATASTATE,data.DATASOURCE,data.JDISTRICT,
                data.JTITLE, data.REGTYPE,data.PROPOSER,data.JLOCATION);
            job_bases.push(job_base);

        return job_bases;

    };

    bulidJOB_SQRXXB_LINK(obj):Array<JOB_SQRXXB_LINK> {

        let datas = obj.QLRXX;
        if (datas === null || datas === void 0)
            return null;

        let job_sqrxxb_links : Array<JOB_SQRXXB_LINK> = new Array<JOB_SQRXXB_LINK>();

        for (let i = 0 ; i < datas.length;i++){
            let data = datas[i];
            let job_sqrxxb_link : JOB_SQRXXB_LINK = new JOB_SQRXXB_LINK(data.XH,data.LX,data.MC,data.ZJZL,data.ZJHM,
                data.DWXZ,data.LXR,data.LXDH,data.SSHY,data.GJDQ,data.HJSZSS,data.TXDZ,data.GYQK,data.QLBL,data.DZYJ,
                data.YB,data.XB,data.FRMC,data.FRZJZL,data.FRZJHM,data.DLRMC,data.DLRZJZL,data.DLRZJHM,data.DLRDH,
                data.DLJG,data.GZDW);
            job_sqrxxb_links.push(job_sqrxxb_link);
        }

        return job_sqrxxb_links;

    };

    buildBackObject(obj):any {

        let backObject :BackObject = {
            "JOB_BASE":Array<JOB_BASE>(),
            "JOB_SQRXXB_LINK.IQLR":Array<JOB_SQRXXB_LINK>()
        };
        backObject["JOB_SQRXXB_LINK.UUU"] = "1";


        backObject.JOB_BASE = this.buildJOB_BASE(obj);
        backObject["JOB_SQRXXB_LINK.IQLR"] = this.bulidJOB_SQRXXB_LINK(obj);

       return backObject;
    };




}

