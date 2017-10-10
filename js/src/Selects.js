import React from "react";

import { chunk } from "./utils";

const Selects = ({ noteSpns, handleChange, submit, selected }) =>
    <div>
        <div>
            {chunk(noteSpns, 17).map((octave, index) =>
                <select id={"octave" + index} size={octave.length} multiple defaultValue={[]} onChange={handleChange}>
                    {octave.map(spn =>
                        <option value={spn}>{spn}</option>
                    )}
                </select>
            )}
        </div>
        <div>
            <button onClick={submit}>Button</button>
        </div>
        <div>

        </div>
    </div>
;

export default Selects;
